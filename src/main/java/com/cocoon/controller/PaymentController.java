package com.cocoon.controller;

import com.cocoon.entity.payment.AuthorizationResponse;
import com.cocoon.entity.payment.Payment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class PaymentController {

    private WebClient webClient = WebClient.builder().baseUrl("https://api.yapily.com").build();
    //private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

    @GetMapping("/payments")
    public Mono<AuthorizationResponse> createWebClient() {
        Payment payment1 = new Payment();
        System.out.println("payment1.toString() = " + payment1.toString());
        return webClient
                .post()
                .uri("https://api.yapily.com/payment-auth-requests")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Basic ODZlM2ZmZWEtNjFkOC00MTQ5LTk2NmMtM2YzMjFiZWJhYTEyOmZkYTdkZGFlLTE5OWEtNDM3ZS1iMTRkLTI2ZmRjNGI2MmU4Nw==")
                .body(Mono.just(payment1), Payment.class)
                .retrieve()
                .bodyToMono(AuthorizationResponse.class);
    }

    @GetMapping("/payments/{id}/details")
    public Mono<Payment> readMonoWithWebClient(@PathVariable("id") String id){
        return webClient
                .get()
                .uri("/payments/{id}/details", id)
                .retrieve()
                .bodyToMono(Payment.class);

    }

    @GetMapping("/institutions")
    public Mono<Object> readMonoWithInstitutions(){
        return webClient
                .get()
                .uri("/institutions")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Basic ODZlM2ZmZWEtNjFkOC00MTQ5LTk2NmMtM2YzMjFiZWJhYTEyOmZkYTdkZGFlLTE5OWEtNDM3ZS1iMTRkLTI2ZmRjNGI2MmU4Nw==")
                .retrieve()
                .bodyToMono(Object.class);

    }
}
