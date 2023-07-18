package bd.gov.lims.user.controller;

import bd.gov.lims.common.dto.UserDto;
import bd.gov.lims.common.param.UserParam;
import bd.gov.lims.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public Mono<UserDto> persist(@RequestBody UserParam userParam) {
        return userService.persistUser(userParam);
    }


}
