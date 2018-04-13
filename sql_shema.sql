CREATE DATABASE test;
drop table IF EXISTS city ;
drop table IF EXISTS country ;
CREATE TABLE country (id serial Primary key, country_name varchar);
CREATE TABLE city  (id SERIAL Primary key,city_name varchar,idcountry integer REFERENCES country (id));
