package ma.enset.control;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlApplication.class, args);
	}
	@Bean
	CommandLineRunner strat()
	{
		return args -> {
			System.out.println("test the DAO");
		};
	}
}
