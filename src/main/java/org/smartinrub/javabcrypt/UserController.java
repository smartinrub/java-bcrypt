package org.smartinrub.javabcrypt;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/save")
    public ModelAndView index() {
        return new ModelAndView("index", "user", new User());
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUser(@ModelAttribute("user") User user) {
        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
        return "result";
    }

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
}
