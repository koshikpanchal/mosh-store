create table user_tags
(
    user_id bigint not null,
    tag_id int not null,
    primary key (user_id, tag_id),
    foreign key (user_id) references users (id) on DELETE  cascade,
    foreign key (tag_id) references  tags (id) on delete cascade
)