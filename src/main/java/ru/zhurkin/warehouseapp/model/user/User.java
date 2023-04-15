package ru.zhurkin.warehouseapp.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.MappedCollection;
import ru.zhurkin.warehouseapp.model.action.Action;
import ru.zhurkin.warehouseapp.model.action.ActionDetails;
import ru.zhurkin.warehouseapp.model.generic.GenericModel;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class User extends GenericModel {

    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;

    @MappedCollection(idColumn = "user_id")
    private Set<Action> actions = new HashSet<>();

    @MappedCollection(idColumn = "seller_id")
    private Set<ActionDetails> actionDetails = new HashSet<>();

}
