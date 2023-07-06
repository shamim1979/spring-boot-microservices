package bd.gov.lims.user.controller;

import bd.gov.lims.user.entity.User;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping(value = "/hello/{id}", params = "username")
    public ResponseEntity<String> hello(
                @PathVariable(name = "id") String id,
                @Spec(path = "username", spec = LikeIgnoreCase.class) Specification<User> specification
            ) {
        System.out.println("the content is correct");
        return ResponseEntity.ok("hello users");
    }

    @PostMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

}
