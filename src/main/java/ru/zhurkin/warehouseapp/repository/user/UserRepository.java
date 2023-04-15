package ru.zhurkin.warehouseapp.repository.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zhurkin.warehouseapp.model.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByLogin(String login);
}
