package sia.learningplatform.controllers;

import sia.learningplatform.entities.User;
import sia.learningplatform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Learning Platform is running! 🚀";
    }
}
