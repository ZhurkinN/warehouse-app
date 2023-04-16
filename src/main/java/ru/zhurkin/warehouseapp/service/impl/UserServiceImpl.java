package ru.zhurkin.warehouseapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.model.user.User;
import ru.zhurkin.warehouseapp.repository.user.RoleRepository;
import ru.zhurkin.warehouseapp.repository.user.UserRepository;
import ru.zhurkin.warehouseapp.service.UserService;
import ru.zhurkin.warehouseapp.support.exception.UserAlreadyExistsException;

import static ru.zhurkin.warehouseapp.support.constant.ErrorMessagesKeeper.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User add(User user) {

        roleRepository.findById(user.getRole().getId())
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));

        if (userRepository.existsByLogin(user.getLogin())) {
            throw new UserAlreadyExistsException(LOGIN_ALREADY_EXISTS);
        }
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }
}
