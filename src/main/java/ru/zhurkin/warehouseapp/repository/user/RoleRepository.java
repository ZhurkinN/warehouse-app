package ru.zhurkin.warehouseapp.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhurkin.warehouseapp.model.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
