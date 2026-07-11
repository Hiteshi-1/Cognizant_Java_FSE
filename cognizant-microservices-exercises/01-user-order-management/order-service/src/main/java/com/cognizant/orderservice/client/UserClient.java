package com.cognizant.orderservice.client;

import com.cognizant.orderservice.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserClient {

    private final WebClient userServiceWebClient;

    // Fetches the user from user-service; blocks since order-service exposes a
    // synchronous (MVC) REST API, but the call itself is non-blocking WebClient.
    public UserDTO getUserById(Long userId) {
        return userServiceWebClient.get()
                .uri("/api/users/{id}", userId)
                .retrieve()
                .bodyToMono(UserDTO.class)
                .onErrorResume(e -> Mono.empty())
                .block();
    }
}
