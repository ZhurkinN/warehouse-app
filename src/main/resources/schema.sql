create table if not exists role (
    id          bigint          not null auto_increment unique,
    role_name   VARCHAR(100)    not null,
    primary key (id)
);

create table if not exists user (
    id          bigint          not null auto_increment unique,
    login       varchar(255)    not null,
    password    varchar(255)    not null,
    first_name  varchar(255)    not null,
    middle_name varchar(255),
    last_name   varchar(255),
    role_id     bigint          not null,
    primary key (id),
    constraint fk_user_role_id
        foreign key (role_id) references role (id)
);

create table if not exists status_type (
    id          bigint          not null auto_increment unique,
    status_name varchar(100)    not null,
    primary key (id)
);

create table if not exists action_type (
    id          bigint          not null auto_increment unique,
    type_name   varchar(100)    not null,
    primary key (id)
);

create table if not exists product (
    id              bigint          not null auto_increment unique,
    title           varchar(255)    not null,
    category        varchar(255),
    description     varchar(255),
    quantity_left   bigint          not null,
    measure_unit    varchar(100),
    price           bigint          not null,
    primary key (id)
);

create table if not exists action (
    id              bigint      not null auto_increment unique,
    user_id         bigint      not null,
    product_id      bigint      not null,
    type_id         bigint      not null,
    status_id       bigint      not null,
    action_date     timestamp   not null,
    description     varchar(255),
    quantity        bigint      not null,
    primary key (id),
    constraint fk_action_user_id
        foreign key (user_id) references user (id),
    constraint fk_action_product_id
        foreign key (product_id) references product (id),
    constraint fk_action_type_id
        foreign key (type_id) references action_type (id),
    constraint fk_action_status_id
        foreign key (status_id) references status_type (id)
);

create table if not exists action_details (
    action_id       bigint      not null,
    seller_id       bigint      not null,
    close_date      timestamp,
    total_price     bigint,
    primary key (action_id, seller_id),
    constraint fk_details_action
        foreign key (action_id) references action (id),
    constraint fk_details_user
        foreign key (seller_id) references user (id)
);

create table if not exists provider (
    id                  bigint          not null,
    name                varchar(150)    not null,
    address             varchar(150),
    telephone_number    varchar(50),
    email               varchar(100),
    primary key (id)
);

create table if not exists product_providers(
    product_id bigint not null,
    provider_id bigint not null,
    primary key (product_id, provider_id),
    constraint fk_providers
        foreign key (provider_id) references provider (id),
    constraint fk_products
        foreign key (product_id) references product (id)
)