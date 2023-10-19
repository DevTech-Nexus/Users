package User.controller;


import User.model.User;
import User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("users")
public class UserController {


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        if (registeredUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists. Please choose a different username.");
        }
    }

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(loginUser);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username and password combination is invalid");
        }

    }

    @GetMapping(path = "/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


}
