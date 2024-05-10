create table user_
(
    uuid UUID not null,
    email varchar(255),
    login varchar(255),
    password varchar(255),
    name varchar(255),
    primary key (uuid)
);