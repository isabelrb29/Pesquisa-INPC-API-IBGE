package com.example.test_api_IBGE;

import com.example.test_api_IBGE.service.ApiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class TestApiIbgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApiIbgeApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ApiService apiService) {
		return args -> {
			apiService.getInpcValue()
					.doOnNext(value -> System.out.println("Valor INPC: " + value))
					.block(); // Bloca a execução para esperar a resposta
		};
	}

}
