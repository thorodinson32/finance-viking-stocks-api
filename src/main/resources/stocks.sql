create database stocks;

use stocks;
create table follows (
            follow_id int NOT NULL AUTO_INCREMENT,
            username varchar(20) NOT NULL ,
            symbol varchar(10) NOT NULL,
            PRIMARY KEY (follow_id)
);