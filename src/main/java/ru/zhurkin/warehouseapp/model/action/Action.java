package ru.zhurkin.warehouseapp.model.action;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.MappedCollection;
import ru.zhurkin.warehouseapp.model.generic.GenericModel;

@Getter
@Setter
@NoArgsConstructor
public class Action extends GenericModel {

    private String description;
    private Integer quantity;

    @MappedCollection(idColumn = "action_id")
    private ActionDetails actionDetails;
}
