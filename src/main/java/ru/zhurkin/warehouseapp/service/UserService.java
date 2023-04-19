package ru.zhurkin.warehouseapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.model.user.User;
import ru.zhurkin.warehouseapp.repository.RoleRepository;
import ru.zhurkin.warehouseapp.repository.UserRepository;
import ru.zhurkin.warehouseapp.service.generic.GenericService;
import ru.zhurkin.warehouseapp.support.exception.UserAlreadyExistsException;

import java.util.List;

import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.*;

@Service
@RequiredArgsConstructor
public class UserService extends GenericService<User> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User add(User user) {

        roleRepository.findById(user.getRole().getId())
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));

        if (userRepository.existsByLogin(user.getLogin())) {
            throw new UserAlreadyExistsException(LOGIN_ALREADY_EXISTS);
        }
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        roleRepository.findById(user.getRole().getId())
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));

        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        userRepository.deleteById(id);
    }

}
