package ru.zhurkin.warehouseapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhurkin.warehouseapp.model.User;
import ru.zhurkin.warehouseapp.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/hello")
    public Iterable<User> hello() {
        return userRepository.findAll();
    }
}
