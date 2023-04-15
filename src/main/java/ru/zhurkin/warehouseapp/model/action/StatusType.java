package ru.zhurkin.warehouseapp.model.action;

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
public class StatusType extends GenericModel {

    private String statusName;

    @MappedCollection(idColumn = "status_type_id")
    private Set<Action> actions = new HashSet<>();
}
