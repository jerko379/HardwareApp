
create table if not exists hardware (
    id identity ,
    code varchar(50) not null unique ,
    name varchar(100) not null,
    tip varchar(50) not null,
    price numeric not null,
    quantity integer not null
    );
