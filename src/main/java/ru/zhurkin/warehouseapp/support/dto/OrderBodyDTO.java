package ru.zhurkin.warehouseapp.support.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.zhurkin.warehouseapp.support.dto.generic.GenericDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderBodyDTO extends GenericDTO {

    private Long managerId;
    private Long assistantId;
    private Long orderTypeId;
    private Long statusTypeId;
    private String description;
    private String contactNumber;
    private Boolean isApproved;
    private List<Long> orderProductIds;
    private Set<Long> orderDetailIds;

    public OrderBodyDTO(Long id,
                        String createdBy,
                        LocalDateTime createdWhen,
                        Boolean isDeleted,
                        String deletedBy,
                        LocalDateTime deletedWhen,
                        Long managerId,
                        Long assistantId,
                        Long orderTypeId,
                        Long statusTypeId,
                        String description,
                        String contactNumber,
                        Boolean isApproved,
                        List<Long> orderProductIds,
                        Set<Long> orderDetailIds) {
        super(id, createdBy, createdWhen, isDeleted, deletedBy, deletedWhen);
        this.managerId = managerId;
        this.assistantId = assistantId;
        this.orderTypeId = orderTypeId;
        this.statusTypeId = statusTypeId;
        this.description = description;
        this.contactNumber = contactNumber;
        this.isApproved = isApproved;
        this.orderProductIds = orderProductIds;
        this.orderDetailIds = orderDetailIds;
    }
}
