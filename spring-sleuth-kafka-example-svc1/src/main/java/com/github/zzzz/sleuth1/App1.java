package com.github.zzzz.sleuth1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.github.zzzz.sleuth1.service.MyMessageListener;

@EnableBinding({ Processor.class })
@SpringBootApplication
@ComponentScan({ 
	"com.github.zzzz.sleuth1.service" 
})
public class App1
{
	public static void main(String[] args)
	{
		SpringApplication.run(App1.class, args);
	}
}
