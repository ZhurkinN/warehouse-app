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
    User's API responses
     */
    public static final String USER_WAS_DELETED = "User was successfully deleted!";

    /*
    Order's API errors
     */
    public static final String ORDER_NOT_FOUND = "Order wasn't found!";
    public static final String ORDER_TYPE_NOT_FOUND = "Order type wasn't found!";
    public static final String STATUS_TYPE_NOT_FOUND = "Status type wasn't found!";
    public static final String PRODUCT_NOT_FOUND = "Product wasn't found!";

    public static final String ORDER_WAS_DELETED = "Order was deleted!";

    public static final String RECORD_WAS_DELETED = "Record was deleted!";
}

