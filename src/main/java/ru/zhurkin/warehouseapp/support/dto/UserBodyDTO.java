package ru.zhurkin.warehouseapp.support.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.zhurkin.warehouseapp.support.dto.generic.GenericDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserBodyDTO extends GenericDTO {

    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Long roleId;

    public UserBodyDTO(Long id,
                       String createdBy,
                       LocalDateTime createdWhen,
                       String login,
                       String password,
                       String firstName,
                       String middleName,
                       String lastName,
                       String gender,
                       Long roleId) {
        super(id, createdBy, createdWhen);
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.roleId = roleId;
    }
}
