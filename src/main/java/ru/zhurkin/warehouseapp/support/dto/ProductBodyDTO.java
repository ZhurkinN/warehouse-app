package ru.zhurkin.warehouseapp.support.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.zhurkin.warehouseapp.support.dto.generic.GenericDTO;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProductBodyDTO extends GenericDTO {

    private String title;
    private String category;
    private String description;
    private Integer quantityLeft;
    private String measureUnit;
    private Double price;
    private String warehousePosition;
    private List<Long> providerIds;
    private List<Long> orderProductsIds;

    public ProductBodyDTO(Long id,
                          String createdBy,
                          LocalDateTime createdWhen,
                          Boolean isDeleted,
                          String deletedBy,
                          LocalDateTime deletedWhen,
                          String title,
                          String category,
                          String description,
                          Integer quantityLeft,
                          String measureUnit,
                          Double price,
                          String warehousePosition,
                          List<Long> providerIds,
                          List<Long> orderProductsIds) {
        super(id, createdBy, createdWhen, isDeleted, deletedBy, deletedWhen);
        this.title = title;
        this.category = category;
        this.description = description;
        this.quantityLeft = quantityLeft;
        this.measureUnit = measureUnit;
        this.price = price;
        this.warehousePosition = warehousePosition;
        this.providerIds = providerIds;
        this.orderProductsIds = orderProductsIds;
    }
}
