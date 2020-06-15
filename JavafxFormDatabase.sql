create database javafxform;
use javafxform;

CREATE TABLE client(
clientID INTEGER NOT NULL auto_increment primary key,
nomPrenom varchar(50) NOT NULL,
email varchar(50) NOT NULL,
pass varchar(50) NOT NULL,
phone varchar(50) NOT NULL,
date_creation TIMESTAMP DEFAULT current_timestamp
) ENGINE=INNODB;

INSERT INTO client(nomPrenom,email,pass,phone)
values ('Ahmed Al Asseri', 'ahmedAl.asseri@gmail.com','A88CE8F','+21264857444'),
('Mohammed Bendada','mohammed.elbachiri@gmail.com','123456','+212626975768');

select * from client;