package bg.stoyank.footballtix.controller;

import bg.stoyank.footballtix.model.User;
import bg.stoyank.footballtix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    private void registerUser(@RequestBody User user) {
        this.userService.registerUser(user);
    }
}
