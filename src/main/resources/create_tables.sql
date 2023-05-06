drop table if exists product_providers;
drop table if exists provider;
drop table if exists order_products;
drop table if exists order_details;
drop table if exists `order`;
drop table if exists product;
drop table if exists order_type;
drop table if exists status_type;
drop table if exists user;
drop table if exists role;


create table role
(
    id        bigint auto_increment primary key,
    role_name varchar(255) not null
);

create table user
(
    id           bigint auto_increment
        primary key,
    created_by   varchar(150) default 'Nikita Zhurkin'  null,
    created_when timestamp    default CURRENT_TIMESTAMP null,
    deleted_by   varchar(150)                           null,
    deleted_when timestamp                              null,
    is_deleted   tinyint(1)   default 0                 null,
    first_name   varchar(255)                           not null,
    gender       varchar(255)                           null,
    last_name    varchar(255)                           null,
    login        varchar(255)                           not null,
    middle_name  varchar(255)                           null,
    password     varchar(255)                           not null,
    role_id      bigint                                 not null,
    constraint UK_login
        unique (login),
    constraint FK_user_role
        foreign key (role_id) references role (id)
);

create table status_type
(
    id          bigint       not null auto_increment primary key,
    status_name varchar(255) not null
);

create table order_type
(
    id         bigint       not null auto_increment primary key,
    order_name varchar(255) not null
);

create table product
(
    id                 bigint                                 not null auto_increment primary key,
    created_by         varchar(150) default 'Nikita Zhurkin'  null,
    created_when       timestamp    default CURRENT_TIMESTAMP null,
    deleted_by         varchar(150)                           null,
    deleted_when       timestamp                              null,
    is_deleted         tinyint(1)   default 0                 null,
    category           varchar(255)                           null,
    description        varchar(255)                           null,
    measure_unit       varchar(255)                           null,
    price              double                                 not null,
    quantity_left      int                                    not null,
    title              varchar(255)                           not null,
    warehouse_position varchar(255)                           not null,
    check ((`price` > 0) and (`quantity_left` >= 0))
);

create table orders
(
    id             bigint                                 not null auto_increment primary key,
    created_by     varchar(150) default 'Nikita Zhurkin'  null,
    created_when   timestamp    default CURRENT_TIMESTAMP null,
    deleted_by     varchar(150)                           null,
    deleted_when   timestamp                              null,
    is_deleted     tinyint(1)   default 0                 null,
    contact_number varchar(255)                           null,
    description    varchar(255)                           null,
    is_approved    tinyint(1)   default 0                 null,
    assistant_id   bigint                                 not null,
    manager_id     bigint                                 not null,
    order_type_id  bigint                                 not null,
    status_type_id bigint       default 1                 not null,
    constraint FK_order_type
        foreign key (order_type_id) references order_type (id),
    constraint FK_order_assistant
        foreign key (assistant_id) references user (id),
    constraint FK_order_manager
        foreign key (manager_id) references user (id),
    constraint FK_order_status
        foreign key (status_type_id) references status_type (id)
);


create table order_products
(
    id         bigint auto_increment primary key,
    quantity   int    not null,
    order_id   bigint not null,
    product_id bigint not null,
    constraint FK_products_order
        foreign key (order_id) references orders (id),
    constraint FK_products_product
        foreign key (product_id) references product (id),
    check (`quantity` > 0)
);


create table order_details
(
    id          bigint auto_increment primary key,
    close_date  timestamp                           null,
    start_date  timestamp default CURRENT_TIMESTAMP null,
    total_price bigint    default 0                 not null,
    order_id    bigint                              not null,
    worker_id   bigint                              not null,
    constraint FK_details_order
        foreign key (order_id) references orders (id),
    constraint FK_details_user
        foreign key (worker_id) references user (id)
);

create table provider
(
    id               bigint                                 not null auto_increment primary key,
    created_by       varchar(150) default 'Nikita Zhurkin'  null,
    created_when     timestamp    default CURRENT_TIMESTAMP null,
    deleted_by       varchar(150)                           null,
    deleted_when     timestamp                              null,
    is_deleted       tinyint(1)   default 0                 null,
    address          varchar(255)                           null,
    email            varchar(255)                           null,
    name             varchar(255)                           not null,
    telephone_number varchar(255)                           null
);

create table product_providers
(
    provider_id bigint not null,
    product_id  bigint not null,
    constraint FK_products_provider
        foreign key (provider_id) references provider (id),
    constraint FK_providers_product
        foreign key (product_id) references product (id)
);
