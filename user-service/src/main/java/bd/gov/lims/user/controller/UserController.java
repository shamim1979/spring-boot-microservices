package bd.gov.lims.user.controller;

import bd.gov.lims.common.dto.Tester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final Tester tester;

    @Autowired
    UserController(Tester tester) {
        this.tester = tester;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        tester.test();
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/hello")
    public ResponseEntity<String> hello(@RequestBody String data) {
        return ResponseEntity.ok("hello");
    }

}
