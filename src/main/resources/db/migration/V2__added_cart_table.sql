create table cart_items
(
    id         bigint auto_increment
        primary key,
    cart_id    binary(16)    null,
    product_id bigint        not null,
    quantity   int default 1 null,
    constraint cart_items_cart_product_unique
        unique (product_id, cart_id),
    constraint cart_items_carts_id_fk
        foreign key (cart_id) references carts (id)
            on delete cascade,
    constraint cart_items_products_id_fk
        foreign key (product_id) references products (id)
            on delete cascade
);
