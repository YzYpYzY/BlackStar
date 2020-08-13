-- *********************************************
-- * SQL MySQL generation                      
-- *--------------------------------------------
-- * DB-MAIN version: 10.0.3              
-- * Generator date: Aug 17 2017              
-- * Generation date: Mon Nov 13 23:29:38 2017 
-- * LUN file: C:\users\YzYpYzY\Desktop\DocToSend\Java Web.lun 
-- * Schema: REL/1-1 
-- ********************************************* 


-- Database Section
-- ________________ 

-- create database blackstardb;
use blackstardb;


-- Tables Section
-- _____________ 

create table orderline (
     id int not null AUTO_INCREMENT,
     quantity int not null constraint orderline_positive_quantity check (quantity > 0),
     payedprice double not null constraint orderline_positive_payedPrice check (payedPrice > 0),
     productid int not null,
     productorderid int not null,
     constraint id_orderline_id primary key (id));

create table description (
     id int not null AUTO_INCREMENT,
     content varchar(700) not null,
     productid int not null,
     languageid int not null,
     constraint id_description_id primary key (id));

create table language (
     id int not null AUTO_INCREMENT,
     isocode varchar(2) not null UNIQUE,
     constraint id_language_id primary key (id));

create table languagelabel (
     id int not null AUTO_INCREMENT,
     content varchar(50) not null,
     languageid int not null,
     constraint id_languagelabel_id primary key (id));

create table productorder (
     id int not null AUTO_INCREMENT,
     state varchar(10) not null,
     promotionamount double not null constraint productorder_positive_promotionamount check (promotionamount >= 0),
     date date not null,
     userid varchar(50) not null,
     constraint id_productorder_id primary key (id));

create table product (
     id int not null AUTO_INCREMENT,
     price double not null constraint product_positive_price check (price > 0),
     photo varchar(50) not null,
     constraint id_product_id primary key (id));

create table productlabel (
     id int not null AUTO_INCREMENT,
     content varchar(50) not null,
     productid int not null,
     languageid int not null,
     constraint id_productlabel_id primary key (id));

create table user (
     username varchar(50) not null,
     password varchar(250) not null,
     authorities varchar(50) not null,
     accountnonexpired boolean,
     accountnonlocked boolean,
     credentialsnonexpired boolean,
     enabled boolean,
     firstname varchar(50) not null,
     lastname varchar(50) not null,
     email varchar(50) not null,
     deliveredaddress varchar(200) not null,
     phone varchar(15) not null,
     vatnum varchar(20),
     society varchar(50),
     languageid int not null,
     constraint id_user_id primary key (username),
     constraint Sid_user_id unique (email));


-- Constraints Section
-- ___________________ 

alter table orderline add constraint FKproductLine_FK
     foreign key (productid)
     references product (id);

alter table orderline add constraint FKproductorder_FK
     foreign key (productorderid)
     references productorder (id);

alter table description add constraint FKTradsdescription_FK
     foreign key (productid)
     references product (id);

alter table description add constraint FKTraddescription_FK
     foreign key (languageid)
     references language (id);

alter table languagelabel add constraint FKTradlanguage_FK
     foreign key (languageid)
     references language (id);

alter table productorder add constraint FKClient_FK
     foreign key (userid)
     references user (username);

alter table productlabel add constraint FKTrads_FK
     foreign key (productid)
     references product (id);

alter table productlabel add constraint FKTrad_FK
     foreign key (languageid)
     references language (id);

alter table user add constraint FKlanguagePref_FK
     foreign key (languageid)
     references language (id);


-- Index Section
-- _____________ 

create unique index id_orderline_IND
     on orderline (id);

create index FKproductLine_IND
     on orderline (productid);

create index FKproductorder_IND
     on orderline (productorderid);

create unique index id_description_IND
     on description (id);

create index FKTradsdescription_IND
     on description (productid);

create index FKTraddescription_IND
     on description (languageid);

create unique index id_language_IND
     on language (id);

create unique index id_languagelabel_IND
     on languagelabel (id);

create index FKTradlanguage_IND
     on languagelabel (languageid);

create unique index id_productorder_IND
     on productorder (id);

create index FKClient_IND
     on productorder (userid);

create unique index id_product_IND
     on product (id);

create unique index id_productlabel_IND
     on productlabel (id);

create index FKTrads_IND
     on productlabel (productid);

create index FKTrad_IND
     on productlabel (languageid);

create unique index id_user_IND
     on user (username);

create unique index Sid_user_IND
     on user (Email);

create index FKlanguagePref_IND
     on user (languageid);

