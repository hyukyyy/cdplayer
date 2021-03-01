package com.cdplayer.cdplayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
public class CdplayerApplication {
	@GetMapping("/")
	public static void main(String[] args) {
		SpringApplication.run(CdplayerApplication.class, args);
	}

}
