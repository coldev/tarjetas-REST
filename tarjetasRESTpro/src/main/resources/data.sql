 

DROP TABLE IF EXISTS clientes;

CREATE TABLE  clientes (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  celular varchar(30) NOT NULL,
  direccion varchar(100) NOT NULL,
  documento varchar(30) NOT NULL,
  email varchar(50) NOT NULL,
  estadocivil varchar(1) NOT NULL,
  fechanacimiento varchar(12) NOT NULL,
  nombre varchar(100) NOT NULL,
  sexo varchar(1) NOT NULL,
  tipodocumento varchar(2) NOT NULL
  
); 


INSERT INTO clientes
(id, celular, direccion, documento, email, estadocivil, fechanacimiento, nombre, sexo, tipodocumento) 
VALUES
	(1, '3156565', 'CLL 55 22 11', '456', 'JONY@MAILNESIA.COM', 'S', '2001-01-01', 'CARLOS PEREZ', 'M', 'TI'),
	(2, '5464564564', 'jhhj 77 hgghgh 767 76776', '234234234', 'kjjkkjas@mail', 'S', '2022-09-16', 'yasure', 'F', 'CC'),
	(3, '1321331312321', 'CLL 11 22 33', '145666', 'JKJKJK@MAILN.COM', 'C', '2022-09-02', 'CLARA', 'F', 'CC');
	

DROP TABLE IF EXISTS datacreditos;

CREATE TABLE datacreditos (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  documento varchar(30) NOT NULL,
  tipodocumento varchar(2) NOT NULL
);


INSERT INTO datacreditos (id, documento, tipodocumento) VALUES
	(1, '12345', 'CC'),
	(2, '54321', 'PA');



DROP TABLE IF EXISTS siebels;

CREATE TABLE   siebels (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  documento varchar(30) NOT NULL,
  tipodocumento varchar(2) NOT NULL  
) ;


INSERT INTO siebels (id, documento, tipodocumento) VALUES
	(1, '987', 'CC');




DROP TABLE IF EXISTS solicitudproductos;

CREATE TABLE solicitudproductos (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  aprobado varchar(1) NOT NULL,
  celular varchar(30) NOT NULL,
  direccion varchar(100) NOT NULL,
  documento varchar(30) NOT NULL,
  email varchar(50) NOT NULL,
  estadocivil varchar(1) NOT NULL,
  fechanacimiento varchar(12) NOT NULL,
  fechasolicitud varchar(12) NOT NULL,
  nombre varchar(100) NOT NULL,
  numero varchar(15) DEFAULT NULL,
  sexo varchar(1) NOT NULL,
  tipodocumento varchar(2) NOT NULL,
  tiposolicitud varchar(2) NOT NULL 
);

 
INSERT INTO solicitudproductos
 (id, aprobado, celular, direccion, documento, email, estadocivil, fechanacimiento, fechasolicitud, nombre, numero, sexo, tipodocumento, tiposolicitud) VALUES
	(1, 'S', '21321312321', 'ASDASDASD', '23423423', 'SADSAD@MAILN', 'S', '2022-09-01', '2022-09-17', 'SADASSAD', NULL, 'M', 'CC', 'CA'),
	(2, 'N', '32423423', 'sdfsdf 888 jhjhjh 887', '234234234', 'kjjh@mnnmnm', 'S', '2022-09-02', '2022-09-17', 'sdfsdfsdf', 'qdkCeKRxGDfdUuq', 'M', 'CC', 'CC'),
	(3, 'S', '234324234', 'sadad asd ', '234234234', 'ASSA@JKJKJK', 'S', '2022-09-02', '2022-09-17', 'saasdsa', '007864465320925', 'F', 'CC', 'TM');


