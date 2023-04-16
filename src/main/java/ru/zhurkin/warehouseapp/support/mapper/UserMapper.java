package ru.zhurkin.warehouseapp.support.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.controller.user.model.UserBodyDTO;
import ru.zhurkin.warehouseapp.model.user.User;
import ru.zhurkin.warehouseapp.repository.user.RoleRepository;

import static ru.zhurkin.warehouseapp.support.constant.ErrorMessagesKeeper.ROLE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleRepository roleRepository;

    public User toEntity(UserBodyDTO dto) {
        return new User()
                .setLogin(dto.login())
                .setPassword(dto.password())
                .setLastName(dto.lastName())
                .setMiddleName(dto.middleName())
                .setFirstName(dto.firstName())
                .setRole(roleRepository
                        .findById(dto.roleId())
                        .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND)));
    }

    public UserBodyDTO toDTO(User user) {
        return new UserBodyDTO(user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getRole().getId());
    }
}
