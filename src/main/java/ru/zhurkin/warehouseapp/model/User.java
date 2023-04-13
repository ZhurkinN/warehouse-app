package ru.zhurkin.warehouseapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;

    @MappedCollection(idColumn = "id")
    private Role role;
}
