package com.example.remotecontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.*;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

@Slf4j
@Controller
@SpringBootApplication
public class RemoteControllerApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(RemoteControllerApplication.class);
		builder.headless(false).run(args);
	}

	@Bean
	public CommandLineRunner cmd() {
		return args -> {
			try {
				var interfaceEnum = NetworkInterface.getNetworkInterfaces();
				while(interfaceEnum.hasMoreElements()) {
					for (InterfaceAddress address : interfaceEnum.nextElement().getInterfaceAddresses()) {
						if (address.getAddress().isSiteLocalAddress()) {
                            log.info("Web-interface is available at: http://{}:8080", address.getAddress().getHostAddress());
						}
					}
				}
			} catch (SocketException e) {
				log.error(e.getMessage());
			}
		};
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
