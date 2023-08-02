package bd.gov.lims.utility.controller;

import bd.gov.lims.common.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PreAuthorize("hasRole('USER_ROLE')")
    public Mono<String> hello() {
        return Mono.just("hello");
    }

    @PostMapping("/user")
    public Mono<UserDto> user() {
        Mono<UserDto> userDtoMono = userClient.build()
                .post()
                .uri("/api/users")
                .body(Mono.just(UserDto.builder()
                        .password("sdfsdag")
                        .build()
                ), UserDto.class)
                .retrieve()
                .bodyToMono(UserDto.class);
        return userDtoMono;
    }
}
