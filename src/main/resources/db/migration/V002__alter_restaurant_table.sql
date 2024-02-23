alter table restaurants
    modify is_active bit not null;

alter table restaurants
    modify is_open bit not null;