create table users(
    id bigserial,
    username varchar(30) not null unique,
    password varchar(250) not null,
    email varchar(50),
    primary key (id)
);

create table authorities
(
  id serial PRIMARY KEY ,
  name varchar(80) not null UNIQUE
);

create table users_authorities(
    user_id bigint not null ,
    authority_id bigint not null ,
    primary key (user_id, authority_id),
    foreign key (user_id) references users(id),
    foreign key (authority_id) references authorities(id)
);

create table roles(
    id serial,
    name varchar(50) not null ,
    primary key (id)
);

create table users_roles(
    user_id bigint not null ,
    role_id bigint not null ,
    primary key (user_id, role_id),
    foreign key (user_id) references users(id),
    foreign key (role_id) references roles(id)
);

insert into roles (name)
VALUES
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email) VALUES
('user', '{noop}100', 'email'),
('user2', '{noop}100', 'email2');

insert into users_roles(user_id, role_id) VALUES
                                              (1,1);

insert into authorities (name) VALUES
('READ_PRIVILEGE'), ('WRITE_PRIVILEGE');

insert into users_authorities(user_id, authority_id)
values (1,1), (2,1),(2,2);