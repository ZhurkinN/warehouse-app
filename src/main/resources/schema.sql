drop table if exists product_providers;
drop table if exists provider;
drop table if exists action_details;
drop table if exists action;
drop table if exists product;
drop table if exists action_type;
drop table if exists status_type;
drop table if exists user;
drop table if exists role;


create table role (
    id          bigint          not null auto_increment unique,
    role_name   varchar(100)    not null,
    primary key (id)
);

create table user (
    id          bigint          not null auto_increment unique,
    login       varchar(255)    not null unique,
    password    varchar(255)    not null,
    first_name  varchar(255)    not null,
    middle_name varchar(255),
    last_name   varchar(255),
    role_id     bigint          not null,
    primary key (id),
    constraint fk_user_role_id
        foreign key (role_id)
            references role (id) on delete cascade
);

create table status_type (
    id          bigint          not null auto_increment unique,
    status_name varchar(100)    not null,
    primary key (id)
);

create table action_type (
    id            bigint          not null auto_increment unique,
    action_name   varchar(100)    not null,
    primary key (id)
);

create table product (
    id              bigint          not null auto_increment unique,
    title           varchar(255)    not null,
    category        varchar(255),
    description     varchar(255),
    quantity_left   integer         not null check (quantity_left >= 0),
    measure_unit    varchar(100),
    price           bigint          not null check (price >= 0),
    primary key (id)
);

create table action (
    id              bigint          not null auto_increment unique,
    user_id         bigint          not null,
    product_id      bigint          not null,
    action_type_id  bigint          not null,
    status_type_id  bigint          not null default 1,
    description     varchar(255),
    quantity        integer         not null check (quantity >= 0),
    primary key (id),
    constraint fk_action_user_id
        foreign key (user_id)
            references user (id),
    constraint fk_action_product_id
        foreign key (product_id)
            references product (id),
    constraint fk_action_type_id
        foreign key (action_type_id)
            references action_type (id) on delete cascade,
    constraint fk_action_status_id
        foreign key (status_type_id)
            references status_type (id) on delete cascade
);

create table action_details (
    action_id       bigint      not null,
    seller_id       bigint      not null,
    close_date      timestamp   default null,
    total_price     bigint      default 0,
    primary key (action_id, seller_id),
    constraint fk_details_action
        foreign key (action_id) references action (id),
    constraint fk_details_user
        foreign key (seller_id) references user (id)
);

create table provider (
    id                  bigint          not null auto_increment unique,
    name                varchar(150)    not null,
    address             varchar(150),
    telephone_number    varchar(50),
    email               varchar(100),
    primary key (id)
);

create table product_providers(
    product_id bigint not null,
    provider_id bigint not null,
    primary key (product_id, provider_id),
    constraint fk_providers
        foreign key (provider_id) references provider (id) on delete cascade,
    constraint fk_products
        foreign key (product_id) references product (id) on delete cascade
);
