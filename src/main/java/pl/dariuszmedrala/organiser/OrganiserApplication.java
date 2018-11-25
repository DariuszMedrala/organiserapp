package pl.dariuszmedrala.organiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrganiserApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganiserApplication.class, args);

	}
}
