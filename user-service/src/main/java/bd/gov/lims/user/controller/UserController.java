package bd.gov.lims.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello users");
    }

    @PostMapping("/hello")
    public ResponseEntity<String> hello(@RequestBody String data) {
        return ResponseEntity.ok("hello");
    }

}
