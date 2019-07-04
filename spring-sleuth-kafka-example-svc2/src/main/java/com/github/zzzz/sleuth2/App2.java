package com.github.zzzz.sleuth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableBinding({ Processor.class })
@SpringBootApplication
@ComponentScan({ 
	"com.github.zzzz.sleuth2.service" 
})
public class App2
{
	public static void main(String[] args)
	{
		SpringApplication.run(App2.class, args);
	}
}
