package ru.zhurkin.warehouseapp.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.warehouseapp.controller.user.model.UserBodyDTO;
import ru.zhurkin.warehouseapp.model.user.User;
import ru.zhurkin.warehouseapp.service.UserService;
import ru.zhurkin.warehouseapp.support.mapper.UserMapper;

@Tag(name = "Users",
        description = "Controller for users")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @Operation(method = "addUser", description = "Add new user")
    public UserBodyDTO addUser(@RequestBody UserBodyDTO requestDto) {

        User user = userMapper.toEntity(requestDto);
        return userMapper.toDTO(userService.add(user));
    }

    @GetMapping("/{id}")
    @Operation(method = "getById", description = "Find user by id")
    public UserBodyDTO getById(@PathVariable Long id) {

        return userMapper.toDTO(userService.getById(id));
    }


}
