create table Games (
	id int not null auto_increment,
    name varchar(50),
    cost double,
    subscription bool,
    player_base long,
    platforms varchar(40)
);

create table Teams (
	id int not null auto_increment,
    game_id int not null,
    manager_id int,
    name varchar(40),
    wins int,
    losses int,
    draws int
);

create table Players (
	id int not null auto_increment,
    first_name varchar(20),
    middle_name varchar(20),
    last_name varchar(20),
    display_name varchar(40),
    joined timestamp,
    updated timestamp,
    updated_by varchar(40)
);

create table Stats (
	id int not null auto_increment,
    team_id int not null auto_increment,
    stat varchar(20)
);