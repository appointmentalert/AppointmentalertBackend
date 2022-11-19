create table alert_entity
(
    id         varchar(36) null,
    user_id    varchar(36) not null,
    type_id    varchar(36) not null,
    start_date date        not null,
    end_date   date        not null,
    active     tinyint(1)  not null
);

create table alert_type_entity
(
    id   varchar(36)  null,
    name varchar(255) not null
);

create table user_entity
(
    id       varchar(36)  null,
    username varchar(255) not null,
    password varchar(255) not null
);

