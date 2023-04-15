package ru.zhurkin.warehouseapp.repository.action;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zhurkin.warehouseapp.model.action.Action;

@Repository
public interface ActionRepository extends CrudRepository<Action, Long> {
}
