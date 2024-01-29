-- Table: kitchens
create table kitchens
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

-- Table: payment_methods
create table payment_methods
(
    id          bigint auto_increment
        primary key,
    description varchar(255) not null
);

-- Table: permissions
create table permissions
(
    id          bigint auto_increment
        primary key,
    description varchar(255) not null,
    name        varchar(255) not null
);

-- Table: product_photos
create table product_photos
(
    id           bigint auto_increment
        primary key,
    content_type varchar(255) not null,
    description  varchar(255) null,
    file_name    varchar(255) not null,
    file_size    bigint       not null
);

-- Table: states
create table states
(
    id   bigint auto_increment
        primary key,
    name varchar(255) not null
);

-- Table: cities
create table cities
(
    id       bigint auto_increment
        primary key,
    name     varchar(255) not null,
    state_id bigint       not null,
    constraint FK_cities_states
        foreign key (state_id) references states (id)
);

-- Table: restaurants
create table restaurants
(
    id                   bigint auto_increment
        primary key,
    address_complement   varchar(255)   null,
    address_neighborhood varchar(255)   null,
    address_number       varchar(255)   null,
    address_public_place varchar(255)   null,
    address_zip_code     varchar(255)   null,
    created_at           datetime(6)    null,
    is_active            bit            null,
    is_open              bit            null,
    name                 varchar(255)   not null,
    shipping_fee         decimal(10, 2) not null,
    updated_at           datetime(6)    null,
    address_city_id      bigint         null,
    kitchen_id           bigint         not null,
    constraint FK_restaurants_cities
        foreign key (address_city_id) references cities (id),
    constraint FK_restaurants_kitchens
        foreign key (kitchen_id) references kitchens (id)
);

-- Table: products
create table products
(
    id               bigint auto_increment
        primary key,
    active           bit            not null,
    description      varchar(255)   not null,
    name             varchar(255)   not null,
    price            decimal(10, 2) not null,
    product_photo_id bigint         null,
    restaurant_id    bigint         null,
    constraint UK_products_product_photos
        unique (product_photo_id),
    constraint FK_products_restaurants
        foreign key (restaurant_id) references restaurants (id),
    constraint FK_products_product_photos
        foreign key (product_photo_id) references product_photos (id)
);

-- Table: restaurant_payment_methods
create table restaurant_payment_methods
(
    restaurant_id     bigint not null,
    payment_method_id bigint not null,
    constraint FK_restaurant_payment_methods_restaurants
        foreign key (restaurant_id) references restaurants (id),
    constraint FK_restaurant_payment_methods_payment_methods
        foreign key (payment_method_id) references payment_methods (id)
);

-- Table: users
create table users
(
    id         bigint auto_increment
        primary key,
    created_at datetime(6)  not null,
    email      varchar(255) not null,
    name       varchar(255) not null,
    password   varchar(255) not null
);

-- Table: orders
create table orders
(
    id                   bigint auto_increment
        primary key,
    address_complement   varchar(255)   null,
    address_neighborhood varchar(255)   null,
    address_number       varchar(255)   null,
    address_public_place varchar(255)   null,
    address_zip_code     varchar(255)   null,
    canceled_at          datetime(6)    null,
    confirmed_at         datetime(6)    null,
    created_at           datetime(6)    not null,
    delivered_at         datetime(6)    null,
    shipping_fee         decimal(10, 2) null,
    status               varchar(50)    not null,
    subtotal             decimal(10, 2) null,
    total                decimal(10, 2) not null,
    address_city_id      bigint         null,
    user_id              bigint         not null,
    payment_method_id    bigint         not null,
    restaurant_id        bigint         not null,
    constraint FK_orders_restaurants
        foreign key (restaurant_id) references restaurants (id),
    constraint FK_orders_users
        foreign key (user_id) references users (id),
    constraint FK_orders_payment_methods
        foreign key (payment_method_id) references payment_methods (id),
    constraint FK_orders_cities
        foreign key (address_city_id) references cities (id),
    constraint CK_orders_status_enum
        check (status in ('created', 'confirmed', 'delivered', 'canceled'))
);

-- Table: order_items
create table order_items
(
    id          bigint auto_increment
        primary key,
    note        varchar(255)   null,
    quantity    int            not null,
    total_price decimal(10, 2) not null,
    unit_price  decimal(10, 2) not null,
    order_id    bigint         not null,
    product_id  bigint         not null,
    constraint FK_order_items_orders
        foreign key (order_id) references orders (id),
    constraint FK_order_items_products
        foreign key (product_id) references products (id)
);

-- Table: user_groups
create table user_groups
(
    id       bigint auto_increment
        primary key,
    name     varchar(255) not null,
    user_id  bigint       not null,
    group_id bigint       not null,
    constraint FK_user_groups_groups
        foreign key (group_id) references user_groups (id),
    constraint FK_user_groups_users
        foreign key (user_id) references users (id)
);

-- Table: user_groups_permissions
create table user_groups_permissions
(
    group_id      bigint not null,
    permission_id bigint not null,
    constraint FK_user_groups_permissions_permissions
        foreign key (permission_id) references permissions (id),
    constraint FK_user_groups_permissions_user_groups
        foreign key (group_id) references user_groups (id)
);
