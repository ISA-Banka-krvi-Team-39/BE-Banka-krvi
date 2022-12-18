package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IsaBeApplication {
	public static void main(String[] args) {
		SpringApplication.run(IsaBeApplication.class, args);
	}
}
