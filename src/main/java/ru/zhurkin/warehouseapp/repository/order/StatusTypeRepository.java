package ru.zhurkin.warehouseapp.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhurkin.warehouseapp.model.order.StatusType;

@Repository
public interface StatusTypeRepository extends JpaRepository<StatusType, Long> {
}
