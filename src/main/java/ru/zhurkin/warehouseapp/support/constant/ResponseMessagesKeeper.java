package ru.zhurkin.warehouseapp.support.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseMessagesKeeper {

    /*
    User's API errors
     */
    public static final String ROLE_NOT_FOUND = "Such role wasn't found!";
    public static final String LOGIN_ALREADY_EXISTS = "User with this login already exists!";
    public static final String USER_NOT_FOUND = "User wasn't found!";

    /*
    Order's API errors
     */
    public static final String ORDER_NOT_FOUND = "Order wasn't found!";
    public static final String ORDER_TYPE_NOT_FOUND = "Order type wasn't found!";
    public static final String STATUS_TYPE_NOT_FOUND = "Status type wasn't found!";
    public static final String PRODUCT_NOT_FOUND = "Product wasn't found!";
    public static final String PROVIDER_NOT_FOUND = "Provider wasn't found!";
    public static final String ORDER_PRODUCTS_NOT_FOUND = "Order with this product wasn't found!";
    public static final String ORDER_DETAILS_NOT_FOUND = "This order is not in work!";

    public static final String RECORD_WAS_DELETED = "Record was deleted!";

    public static final String WRONG_ROLE_PERMISSIONS = "No permissions to do this!";
    public static final String WRONG_PRODUCT_PARAMETERS = "Wrong product's parameter/-s!";
    public static final String NOT_ENOUGH_PRODUCTS = "There are not enough product in the warehouse!";
    public static final String PRODUCT_WAS_DELETED = "Product was deleted from the order";
}

