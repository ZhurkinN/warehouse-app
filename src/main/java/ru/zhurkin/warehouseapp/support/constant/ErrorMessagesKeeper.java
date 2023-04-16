package ru.zhurkin.warehouseapp.support.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessagesKeeper {

    /*
    User's API errors
     */
    public static final String ROLE_NOT_FOUND = "Such role wasn't found!";
    public static final String LOGIN_ALREADY_EXISTS = "User with this login already exists!";
    public static final String USER_NOT_FOUND = "User wasn't found!";
}
