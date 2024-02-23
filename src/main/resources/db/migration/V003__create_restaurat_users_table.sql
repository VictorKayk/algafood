-- Table: restaurant_users
create table restaurant_users
(
    restaurant_id bigint not null,
    user_id       bigint not null,
    constraint FK_restaurant_users_restaurants
        foreign key (restaurant_id) references restaurants (id),
    constraint FK_restaurant_users_users
        foreign key (user_id) references users (id)
);
