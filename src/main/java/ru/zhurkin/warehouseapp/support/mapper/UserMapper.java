package ru.zhurkin.warehouseapp.support.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.model.user.User;
import ru.zhurkin.warehouseapp.repository.RoleRepository;
import ru.zhurkin.warehouseapp.support.dto.UserBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.generic.GenericMapper;

import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.ROLE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class UserMapper extends GenericMapper<User, UserBodyDTO> {

    private final RoleRepository roleRepository;

    public User toEntity(UserBodyDTO dto) {
        User user = new User()
                .setLogin(dto.getLogin())
                .setPassword(dto.getPassword())
                .setLastName(dto.getLastName())
                .setMiddleName(dto.getMiddleName())
                .setFirstName(dto.getFirstName())
                .setRole(roleRepository
                        .findById(dto.getRoleId())
                        .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND)));
        user.setId(dto.getId());
        user.setCreatedBy(dto.getCreatedBy());
        user.setCreatedWhen(dto.getCreatedWhen());
        return user;
    }

    public UserBodyDTO toDto(User user) {
        return new UserBodyDTO(
                user.getId(),
                user.getCreatedBy(),
                user.getCreatedWhen(),
                user.getLogin(),
                user.getPassword(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getRole().getId()
        );
    }

}
