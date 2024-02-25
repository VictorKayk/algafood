alter table orders add column uuid varchar(36) not null;

update orders set uuid = uuid();

alter table orders add constraint UK_orders_uuid unique (uuid);
