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
public class ProviderBodyDTO extends GenericDTO {

    private String name;
    private String address;
    private String telephoneNumber;
    private String email;
    private List<Long> productIds;

    public ProviderBodyDTO(Long id,
                           String createdBy,
                           LocalDateTime createdWhen,
                           String name,
                           String address,
                           String telephoneNumber,
                           String email,
                           List<Long> productIds) {
        super(id, createdBy, createdWhen);
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.productIds = productIds;
    }
}
