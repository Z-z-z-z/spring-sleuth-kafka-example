package com.github.zzzz.sleuth2.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MyMessageListener
{
	@Qualifier("output")
	@Autowired
	private MessageChannel outputChannel;

	@StreamListener(value=Sink.INPUT)
	public void handleMessage(String message)
	{
		log.info("\n\nMessage:\n{}\n\n", message);
		
		outputChannel.send(
				MessageBuilder
					.withPayload(message)
					.build()
		);
	}
}
