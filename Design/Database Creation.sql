drop database if exists marketplace;
create database marketplace;
use marketplace;

create table customers(
	id int auto_increment,
    full_name varchar(128),
    email varchar(512) not null,
    password varchar(512) not null,
    cash int not null default 0,
    primary key(id)
);

create table items(
	id  int auto_increment,
    name varchar(512) not null,
    price int not null,
    primary key(id)
);

create table buy(
	customer_id int,
    item_id int,
    paid tinyint,
    foreign key(customer_id) references customers(id),
    foreign key(item_id) references items(id),
    primary key(customer_id, item_id)
);

create table categories(
id int auto_increment,
name varchar(512),
primary key(id)
);

create table items_category(
item_id int,
category_id int,

primary key(item_id, category_id),
foreign key(item_id) references items(id),
foreign key(category_id) references categories(id)
);
