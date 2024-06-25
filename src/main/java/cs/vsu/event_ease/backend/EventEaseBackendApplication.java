package cs.vsu.event_ease.backend;

import cs.vsu.event_ease.backend.service.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventEaseBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventEaseBackendApplication.class, args);

	}

}
