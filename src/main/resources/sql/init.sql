create table if not exists address
(id bigint generated always as identity primary key,
 country varchar not null,
 city varchar not null,
 street varchar not null
);


create table if not exists images
(id uuid  DEFAULT uuid_generate_v4() primary key,
 image bytea
);


create table if not exists client
( id bigint generated always as identity primary key,
  client_name varchar not null,
  client_surname varchar not null,
  birthday date not null,
  gender varchar default 'female' not null check(gender in ('male', 'female')),
    registration_date date not null default current_date,
    address_id bigint not null references address(id)
    );


create table if not exists supplier
(id bigint generated always as identity primary key,
 name varchar not null,
 address_id bigint references address(id) not null,
    phone_number varchar(16) not null
    );


create table if not exists product
(id bigint generated always as identity primary key,
 name varchar not null,
 category varchar not null,
 price int not null,
 available_stock int not null check(available_stock >= 0),
    last_update_date date not null,
    supplier_id bigint not null references supplier(id),
    image_id uuid not null references images(id)
    );

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";






