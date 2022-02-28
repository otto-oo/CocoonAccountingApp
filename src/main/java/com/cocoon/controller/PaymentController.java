package com.cocoon.controller;

import com.cocoon.entity.Payment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class PaymentController {

    private WebClient webClient = WebClient.builder().baseUrl("https://api.yapily.com").build();
    //private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8082").build();

    @PostMapping("/payment")
    public Mono<Payment> createWebClient(@RequestBody Payment payment) {
        return webClient.post()
                .uri("/payment-auth-requests")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Basic ODZlM2ZmZWEtNjFkOC00MTQ5LTk2NmMtM2YzMjFiZWJhYTEyOmZkYTdkZGFlLTE5OWEtNDM3ZS1iMTRkLTI2ZmRjNGI2MmU4Nw==")
                .body(Mono.just(payment), Payment.class)
                .retrieve()
                .bodyToMono(Payment.class);
    }
}
