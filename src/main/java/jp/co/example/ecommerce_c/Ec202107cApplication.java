package jp.co.example.ecommerce_c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Ec202107cApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ec202107cApplication.class, args);
	}

}
