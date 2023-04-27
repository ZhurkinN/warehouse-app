package ru.zhurkin.warehouseapp.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.zhurkin.warehouseapp.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLogin(String login);

    @Query(nativeQuery = true,
            value = "" +
            "select if (count(*) = 0, true, false)\n" +
            "from user\n" +
            "join order_details od on user.id = od.worker_id\n" +
            "where user.id = ?1 and od.close_date IS NULL")
    long canSoftDeleteWorker(Long userId);

    @Query(nativeQuery = true,
            value = "" +
                    "select if (count(*) = 0, true, false)\n" +
                    "from user\n" +
                    "join orders od on user.id = od.assistant_id\n" +
                    "where user.id = ?1 and od.is_approved = 0")
    long canSoftDeleteAssistant(Long id);
}
