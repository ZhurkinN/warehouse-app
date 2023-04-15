package ru.zhurkin.warehouseapp.repository.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zhurkin.warehouseapp.model.user.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
