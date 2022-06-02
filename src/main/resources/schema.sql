

create table if not exists hardware
(
    id       identity  not null unique ,
    code     varchar(50)  not null unique,
    name     varchar(100) not null,
    tip      varchar(50)  not null,
    price    numeric      not null,
    quantity integer      not null
);


create table if not exists review
(
    id          identity not null unique,
    title       varchar(500) not null,
    content     varchar(500) not null,
    rating      number       not null,
    hardware_Id number       not null,
    constraint fk_hardware foreign key (hardware_Id) references hardware (id) on delete cascade on update no action
);

create table if not exists usr
(
    id       identity not null unique,
    username varchar(100) not null unique,
    pass varchar(1000) not null
);
create table if not exists authority
(
    id             identity not null unique,
    authority_name varchar(100) not null unique
);
create table if not exists user_authority
(
    user_id      bigint not null,
    authority_id bigint not null,
    constraint fk_user foreign key (user_id) references usr (id),
    constraint fk_authority foreign key (authority_id) references authority (id)
);


