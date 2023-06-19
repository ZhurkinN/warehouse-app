package ru.zhurkin.warehouseapp.support.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestParametersValidator {

    public static boolean validateProduct(Double price,
                                          Integer quantityLeft) {
        return price > 0 && quantityLeft >= 0;
    }
}
