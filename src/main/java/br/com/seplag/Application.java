package br.com.seplag;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.seplag.services.rest.MicrosoftUpdateServiceRest;

@SpringBootApplication
@EnableScheduling
public class Application {

	@Autowired
	@Qualifier("microsoftUpdateServiceRestImpl")
	public MicrosoftUpdateServiceRest service;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

	@PostConstruct
	public void init() {
		
	}

}
