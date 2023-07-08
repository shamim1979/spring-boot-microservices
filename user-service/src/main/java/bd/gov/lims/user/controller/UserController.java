package bd.gov.lims.user.controller;

import bd.gov.lims.user.dto.UserDto;
import bd.gov.lims.user.entity.User;
import bd.gov.lims.user.service.UserService;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/hello/{id}", params = "username")
    public ResponseEntity<String> hello(
                @PathVariable(name = "id") String id,
                @Spec(path = "username", spec = LikeIgnoreCase.class) Specification<User> specification
            ) {
        System.out.println("the content is correct");
        return ResponseEntity.ok("hello users");
    }

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("hello")
                .log()
                .map(d -> {
            if (d.equals("hello")) {
                throw new RuntimeException("hello exception");
            }
            return d;
        });
    }

    @PostMapping()
    public Mono<UserDto> persist(@RequestBody UserDto userDto) {
        return userService.persistUser(userDto);
    }


}
