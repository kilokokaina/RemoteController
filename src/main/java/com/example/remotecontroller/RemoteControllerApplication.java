package com.example.remotecontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.*;

@Slf4j
@Controller
@SpringBootApplication
public class RemoteControllerApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(RemoteControllerApplication.class);
		builder.headless(false).run(args);
	}

	@GetMapping
	public String index() {
		return "index";
	}

	@Bean
	public Robot cursorRobot() throws AWTException {
		return new Robot();
	}

}
