package ru.zhurkin.warehouseapp.support.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityRoleConstantsKeeper {

    public static final String WORKER = "WORKER";
    public static final String SALES_MANAGER = "MANAGER";
    public static final String ASSISTANT = "ASSISTANT";
    public static final String MODERATOR = "MODERATOR";
}
