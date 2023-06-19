package ru.zhurkin.warehouseapp.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.zhurkin.warehouseapp.model.user.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByLogin(String login);

    boolean existsByLogin(String login);

    @Query(nativeQuery = true,
            value = "select if (count(*) = 0, true, false)\n" +
                    "from user\n" +
                    "join order_details od on user.id = od.worker_id\n" +
                    "where user.id = ?1 and od.close_date IS NULL")
    long canSoftDeleteWorker(Long userId);

    @Query(nativeQuery = true,
            value = "select if (count(*) = 0, true, false)\n" +
                    "from user\n" +
                    "join orders od on user.id = od.assistant_id\n" +
                    "where user.id = ?1 and od.is_approved = 0")
    long canSoftDeleteAssistant(Long id);

    @Query(nativeQuery = true,
            value = "select * " +
                    "from user " +
                    "where role_id = 5")
    List<User> findAllAssistants();

    Page<User> findAllByLoginContainsIgnoreCaseAndIsDeletedFalse(String login, PageRequest pageRequest);
}
