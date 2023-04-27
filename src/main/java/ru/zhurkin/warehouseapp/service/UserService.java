package ru.zhurkin.warehouseapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.model.enums.RoleEnum;
import ru.zhurkin.warehouseapp.model.user.User;
import ru.zhurkin.warehouseapp.repository.user.RoleRepository;
import ru.zhurkin.warehouseapp.repository.user.UserRepository;
import ru.zhurkin.warehouseapp.service.generic.GenericService;
import ru.zhurkin.warehouseapp.support.exception.UserAlreadyExistsException;

import java.time.LocalDateTime;
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
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        userRepository.delete(user);
    }

    @Override
    public boolean softDelete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        boolean isDeleted = false;
        if ((user.getRole().getRoleName().equals(RoleEnum.LOADER.getRoleName())
                || user.getRole().getRoleName().equals(RoleEnum.COLLECTOR.getRoleName()))
                && userRepository.canSoftDeleteWorker(id) == 1) {
            user.setIsDeleted(true);
            user.setDeletedBy("ADMIN");
            user.setDeletedWhen(LocalDateTime.now());
            isDeleted = true;
        } else if (user.getRole().getRoleName().equals(RoleEnum.ASSISTANT.getRoleName())
                && userRepository.canSoftDeleteAssistant(id) == 1) {
            user.setIsDeleted(true);
            user.setDeletedBy("ADMIN");
            user.setDeletedWhen(LocalDateTime.now());
            isDeleted = true;
        } else if (user.getRole().getRoleName().equals(RoleEnum.SALES_MANAGER.getRoleName())
                || user.getRole().getRoleName().equals(RoleEnum.MODERATOR.getRoleName())) {
            user.setIsDeleted(true);
            user.setDeletedBy("ADMIN");
            user.setDeletedWhen(LocalDateTime.now());
            isDeleted = true;
        }
        userRepository.save(user);
        return isDeleted;
    }

    @Override
    public void restore(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        if (user.getIsDeleted()) {
            user.setIsDeleted(false);
            user.setDeletedBy(null);
            user.setDeletedWhen(null);
        }
        userRepository.save(user);
    }

}
