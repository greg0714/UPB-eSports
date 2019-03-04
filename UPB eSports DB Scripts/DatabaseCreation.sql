create table Games (
	id int not null auto_increment primary key,
    name varchar(50),
    cost decimal(5,2),
    subscription boolean,
    microtransactions boolean,
    player_base long,
    platforms varchar(50),
    updated timestamp,
    updated_by varchar(50)
);

create table Teams (
	id int not null auto_increment primary key,
    game_id int not null,
    manager_id int,
    name varchar(50),
    wins int,
    losses int,
    draws int,
    updated timestamp,
    updated_by varchar(50),
    FOREIGN KEY (game_id) REFERENCES Games(id)
);

create table Users (
    email varchar(50) not null primary key,
    password varchar(100) not null,
    enabled boolean not null
);

create table Authorities (
	id int not null auto_increment primary key,
	email varchar(50) not null,
    authority varchar(20) not null,
    FOREIGN KEY (email) REFERENCES Users(email)
);

create table Players (
	id int not null auto_increment primary key,
    user_email varchar(50),
    first_name varchar(20),
    middle_name varchar(20),
    last_name varchar(20),
    display_name varchar(50),
    eligible boolean,
    joined timestamp,
    updated timestamp,
    updated_by varchar(50),
    FOREIGN KEY (user_email) REFERENCES Users(email)
);

create table TeamPlayers (
	id int not null auto_increment primary key,
    team_id int not null,
    player_id int not null,
    FOREIGN KEY (team_id) REFERENCES Teams(id),
    FOREIGN KEY (player_id) REFERENCES Players(id)
);

create table Stats (
	id int not null auto_increment primary key,
    game_id int not null,
    stat varchar(20),
    FOREIGN KEY (game_id) REFERENCES Games(id)
);

create table PlayerStats (
	id int not null auto_increment primary key,
    player_id int not null,
    stat_id int not null,
    updated timestamp,
    updated_by varchar(50),
    value varchar(50),
    value_num decimal,
    FOREIGN KEY (player_id) REFERENCES Players(id),
    FOREIGN KEY (stat_id) REFERENCES Stats(id)
);

create table TeamSchedules (
	id int not null auto_increment primary key,
    team_id int not null,
    event_time timestamp,
    location varchar(100),
    event_type varchar(1),
    comments varchar(1000),
    updated timestamp,
    updated_by varchar(50),
    FOREIGN KEY (team_id) REFERENCES Teams(id)
);

create table TableCodes (
	id int not null auto_increment primary key,
    table_name varchar(50),
    column_name varchar(50),
    value varchar(3),
    description varchar(50)
);
