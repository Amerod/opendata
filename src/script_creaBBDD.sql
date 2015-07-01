drop database if exists db_agenda;
create database db_agenda;
use db_agenda;
create table agenda(
	ID int(8) PRIMARY KEY,
    acte varchar(200),
    descripcio varchar(200),
    email_acte varchar(100),
    web_acte varchar(100),
    a_carrec varchar(300),
    lloc varchar(100),
    data_ini DATETIME,
    data_fi DATETIME,
    gratuit varchar(10),
    preu varchar(300),
    districte varchar(10),
    tipus_acte varchar(200),
    observacions varchar(1000),
    cicle varchar(200),
    descripcio_cicle varchar(600),
    cicle_data_ini DATETIME,
    cicle_data_fi DATETIME,
    x varchar(200),
    y varchar(200),
    longitud varchar(200),
    latitud varchar(200),
    url varchar(100)
);
drop procedure if exists sp_insertAgenda;

delimiter xd
	create procedure sp_insertAgenda(in id int(8),in acte varchar(200),in descripcio varchar(200),in email_acte varchar(100),in web_acte varchar(100),in a_carrec varchar(300),in lloc varchar(100),in data_ini varchar(20),in data_fi varchar(20),in gratuit varchar(10),in preu varchar(300),in districte varchar(50),in tipus_acte varchar(200),in observacions varchar(1000),in cicle varchar(200),in descripcio_cicle varchar(600),in cicle_data_ini DATETIME,in cicle_data_fi DATETIME,in x varchar(200),in y varchar(200),in longitud varchar(200),in latitud varchar(200),in url varchar(100))
    begin
		insert into agenda values(id,acte,descripcio,email_acte,web_acte,a_carrec,lloc,data_ini,data_fi,gratuit,preu,districte,tipus_acte,observacions,cicle,descripcio_cicle,cicle_data_ini,cicle_data_fi,x,y,longitud,latitud,url);
    end;
xd
delimiter ;
