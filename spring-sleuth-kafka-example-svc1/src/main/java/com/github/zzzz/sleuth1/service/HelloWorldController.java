package com.github.zzzz.sleuth1.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.zzzz.sleuth1.model.HelloResponse;

import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HelloWorldController
{
	@Value("${hello.message}")
	private String template;
	private final AtomicLong counter = new AtomicLong();
	
	@Qualifier("output")
	@Autowired
	private MessageChannel outputChannel;
	@Autowired
	private Tracer tracer;

	@GetMapping("/hello")
	@ResponseBody
	public HelloResponse sayHello(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name)
	{
		HelloResponse response = new HelloResponse(counter.incrementAndGet(), String.format(template, name));
		
		log.info(response.getContent());
		
		// set new traceId
		Span newSpan = this.tracer.newTrace().name("new");
		try (Tracer.SpanInScope ws = this.tracer.withSpanInScope(newSpan.start())) 
		{
			log.info("new traceId {}", Long.toHexString(newSpan.context().traceId()));
			
			outputChannel.send(
					MessageBuilder
						.withPayload(response)
						.setHeader("h1", "" + response.getId())
						.build()
			);		
		} 
		finally 
		{
			newSpan.finish();
		}
		
		return response;
	}
}
