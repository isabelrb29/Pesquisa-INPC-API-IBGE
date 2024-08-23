package com.example.test_api_IBGE.service;

import com.example.test_api_IBGE.model.ResponseWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ApiService {
    private final WebClient webClient;

    public ApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://servicodados.ibge.gov.br").build();
    }

    public Mono<String> getInpcValue() {
        return webClient.get()
                .uri("/api/v3/agregados/1736/periodos/202401/variaveis/44?localidades=N1[all]")
                .retrieve()
                .bodyToMono(ResponseWrapper[].class) // Usar ResponseWrapper[] para mapear o array
                .flatMapMany(Flux::fromArray) // Converte o array em um Flux
                .next() // Pega o primeiro item do Flux (equivalente ao map(response -> response[0]))
                .flatMap(wrapper -> {
                    if (wrapper.getResultados() != null && !wrapper.getResultados().isEmpty()) {
                        return Mono.justOrEmpty(wrapper.getResultados().getFirst().getSeries().getFirst().getSerie().get("202401"));
                    }
                    return Mono.empty();
                });
    }
}
