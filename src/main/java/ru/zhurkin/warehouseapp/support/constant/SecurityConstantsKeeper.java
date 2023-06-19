package ru.zhurkin.warehouseapp.support.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityConstantsKeeper {

    public static List<String> RESOURCES_WHITE_LIST = List.of(
            "/resources/**",
            "/js/**",
            "/css/**",
            "/",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/error", "/users"
    );
    public static List<String> PRODUCTS_WHITE_LIST = List.of(
            "/products",
            "/products/search",
            "/products/{id}"
    );
    public static List<String> PROVIDERS_WHITE_LIST = List.of(
            "/providers",
            "/providers/search",
            "/providers/{id}"
    );
    public static List<String> USERS_WHITE_LIST = List.of(
            "/login",
            "/registration",
            "/users",
            "/users/search",//TODO
            "/users/{id}"
    );

    public static List<String> PRODUCTS_PERMITTED_LIST = List.of(
            "/products/add",
            "/products/delete/{id}",
            "/products/update",
            "/products/update/{id}",
            "/products/restore/{id}"
    );
    public static List<String> PROVIDERS_PERMITTED_LIST = List.of(
            "/providers/add",
            "/providers/delete/{id}",
            "/providers/update",
            "/providers/update/{id}",
            "/providers/restore/{id}"
    );
    public static List<String> USERS_PERMITTED_LIST = List.of(
            "/users/add",
            "/users/delete/{id}",//TODO
            "/users/update",//TODO
            "/users/update/{id}",//TODO
            "/users/restore/{id}"//TODO
    );
    public static List<String> ORDERS_PERMITTED_LIST = List.of(
            "/orders",
            "/orders/{id}"

    );
    public static List<String> ORDERS_ASSISTANT_MANAGER_LIST = List.of(
            "/orders/history/{id}"
    );
    public static List<String> ORDERS_MANAGER_LIST = List.of(
            "/orders/add",
            "/orders/delete/{id}",
            "/orders/update",
            "/orders/update/{id}",
            "/orders/restore/{id}",
            "/orders/add-product",
            "/orders/add-product/{id}"
    );
    public static List<String> ORDERS_ASSISTANT_LIST = List.of(
            "/orders/approve/{id}",
            "/orders/decline/{id}"
    );
    public static List<String> ORDERS_WORKER_LIST = List.of(
            "/orders/start/{orderId}",
            "/order-details",
            "/order-details/finish/{orderId}",
            "/orders/available/{id}"
    );


}
