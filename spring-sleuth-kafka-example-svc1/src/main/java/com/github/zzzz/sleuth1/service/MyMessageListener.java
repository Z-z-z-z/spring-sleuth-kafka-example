package com.github.zzzz.sleuth1.service;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MyMessageListener
{
	@StreamListener(value=Sink.INPUT)
	public void handleMessage(String message)
	{
		log.info("\n\nMessage:\n{}\n\n", message);
	}
}
