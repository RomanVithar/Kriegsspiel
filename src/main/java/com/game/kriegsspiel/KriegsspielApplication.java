package com.game.kriegsspiel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class KriegsspielApplication {

	public static void main(String[] args) {
		SpringApplication.run(KriegsspielApplication.class, args);
	}

}
