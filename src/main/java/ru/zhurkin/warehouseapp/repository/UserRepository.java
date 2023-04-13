package ru.zhurkin.warehouseapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zhurkin.warehouseapp.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
