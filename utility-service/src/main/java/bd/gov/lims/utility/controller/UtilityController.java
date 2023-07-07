package bd.gov.lims.utility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/utilities")
public class UtilityController {

    WebClient.Builder userClient;
    @Autowired
    UtilityController(@Qualifier("userClient") WebClient.Builder userClient) {
        this.userClient = userClient;
    }
    @GetMapping("/hello")
    public Mono<String> hello() {
        Mono<String> stringMono = userClient.build().get().uri("/api/users/hello").retrieve().bodyToMono(String.class);
        return stringMono;
    }
}
