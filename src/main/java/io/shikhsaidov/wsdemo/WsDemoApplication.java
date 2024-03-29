package io.shikhsaidov.wsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsDemoApplication.class, args);
	}

}
