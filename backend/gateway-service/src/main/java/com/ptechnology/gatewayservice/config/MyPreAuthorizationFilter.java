package com.ptechnology.gatewayservice.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MyPreAuthorizationFilter implements GlobalFilter {

    private WebClient.Builder webClientBuilder;

    public MyPreAuthorizationFilter(WebClient.Builder webClientBuilder){
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Web filter start");
        HttpCookie cookie = exchange.getRequest().getCookies().getFirst("access_token");
        if(cookie != null) {
            String access_token = cookie.toString().substring("access_token=".length());
            System.out.println("ACCESS_TOKEN: " + access_token);

            return webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8099/api/tokenisvalid")
                    .cookie("access_token", cookie.toString())
                    .retrieve()
                    .bodyToMono(Long.class)
                    .flatMap(f -> {
                        if (f == -1L) {
                            System.out.println("Token expired: " + f.toString());
                            ServerHttpResponse response = exchange.getResponse();
                            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                            return response.setComplete();
                        } else if (f == -2L) {
                            System.out.println("ACCESS TOKEN NOT FOUND: " + f.toString());
                            ServerHttpResponse response = exchange.getResponse();
                            response.setStatusCode(HttpStatus.I_AM_A_TEAPOT);
                            return response.setComplete();
                        }
                        else {
                            System.out.println("Ok: " + f.toString());
                            exchange.getRequest().mutate().header("CUSTOM-REQUEST-HEADER", f.toString()).build();
                            return chain.filter(exchange);
                        }
                    });
        } else if(exchange.getRequest().getPath().toString().equals("/verify")){
            System.out.println("Verify reached!");
            return chain.filter(exchange);
        } else {
            System.out.println("Cookie not found!");
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.NOT_FOUND);
            return response.setComplete();
        }
    }
}
