package ru.zhurkin.warehouseapp.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.MappedCollection;
import ru.zhurkin.warehouseapp.model.generic.GenericModel;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Role extends GenericModel {

    private String roleName;

    @MappedCollection(idColumn = "role_id")
    private Set<User> users = new HashSet<>();
}
