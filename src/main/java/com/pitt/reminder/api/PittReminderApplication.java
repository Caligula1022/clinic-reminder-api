package com.pitt.reminder.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.pitt.reminder.api", "controller", "service"})

public class PittReminderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PittReminderApplication.class, args);
	}

}
