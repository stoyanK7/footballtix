package bg.stoyank.footballtix.controller;

import bg.stoyank.footballtix.model.User;
import bg.stoyank.footballtix.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    private void registerUser(@RequestBody User user) {
        this.userService.createUser(user);
    }
}
