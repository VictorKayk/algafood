create table templates
(
    id         bigint auto_increment primary key,
    name       varchar(255) not null unique,
    body       text not null,
    created_at datetime(6) not null,
    updated_at datetime(6) null
);
