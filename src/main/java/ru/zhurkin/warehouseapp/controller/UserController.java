package ru.zhurkin.warehouseapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhurkin.warehouseapp.controller.generic.GenericController;
import ru.zhurkin.warehouseapp.model.user.User;
import ru.zhurkin.warehouseapp.service.UserService;
import ru.zhurkin.warehouseapp.support.dto.UserBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.UserMapper;

@Tag(name = "Users",
        description = "Controller for users")
@RestController
@RequestMapping("/api/v1/users")
public class UserController extends GenericController<UserBodyDTO, User> {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService,
                          UserMapper userMapper) {
        super(userService, userMapper);
        this.userService = userService;
        this.userMapper = userMapper;
    }


}
