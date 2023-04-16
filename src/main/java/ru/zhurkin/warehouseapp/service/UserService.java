package ru.zhurkin.warehouseapp.service;

import ru.zhurkin.warehouseapp.model.user.User;

public interface UserService {

    User add(User user);
    User getById(Long id);
}
