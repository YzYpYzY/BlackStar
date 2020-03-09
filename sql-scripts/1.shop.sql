-- *********************************************
-- * SQL MySQL generation                      
-- *--------------------------------------------
-- * DB-MAIN version: 10.0.3              
-- * Generator date: Aug 17 2017              
-- * Generation date: Mon Nov 13 23:29:38 2017 
-- * LUN file: C:\Users\YzYpYzY\Desktop\DocToSend\Java Web.lun 
-- * Schema: REL/1-1 
-- ********************************************* 


-- Database Section
-- ________________ 

-- create database blackstardb;
use blackstardb;


-- Tables Section
-- _____________ 

create table OrderLine (
     Id int not null,
     Quantity int not null,
     PayedPrice decimal(10,2) not null,
     ProductId int not null,
     ProductOrderId int not null,
     constraint ID_OrderLine_ID primary key (Id));

create table Description (
     Id int not null,
     Content varchar(300) not null,
     ProductId int not null,
     LanguageId int not null,
     constraint ID_Description_ID primary key (Id));

create table Language (
     Id int not null,
     constraint ID_Language_ID primary key (Id));

create table LanguageLabel (
     Id int not null,
     Content varchar(50) not null,
     LanguageId int not null,
     constraint ID_LanguageLabel_ID primary key (Id));

create table ProductOrder (
     Id int not null,
     State int not null,
     PromotionAmount decimal(10,2) not null,
     Date date not null,
     UserId int not null,
     constraint ID_ProductOrder_ID primary key (Id));

create table Product (
     Id int not null,
     Price decimal(10,2) not null,
     Photo varchar(50) not null,
     constraint ID_Product_ID primary key (Id));

create table ProductLabel (
     Id int not null,
     Content varchar(50) not null,
     ProductId int not null,
     LanguageId int not null,
     constraint ID_ProductLabel_ID primary key (Id));

create table User (
     Id int not null,
     Email varchar(50) not null,
     PasswordHash varchar(50) not null,
     DeliveredAddress varchar(200) not null,
     Phone varchar(15) not null,
     VATNum varchar(20),
     Society varchar(50),
     LanguageId int not null,
     constraint ID_User_ID primary key (Id),
     constraint SID_User_ID unique (Email));


-- Constraints Section
-- ___________________ 

alter table OrderLine add constraint FKProductLine_FK
     foreign key (ProductId)
     references Product (Id);

alter table OrderLine add constraint FKProductOrder_FK
     foreign key (ProductOrderId)
     references ProductOrder (Id);

alter table Description add constraint FKTradsDescription_FK
     foreign key (ProductId)
     references Product (Id);

alter table Description add constraint FKTradDescription_FK
     foreign key (LanguageId)
     references Language (Id);

alter table LanguageLabel add constraint FKTradLanguage_FK
     foreign key (LanguageId)
     references Language (Id);

alter table ProductOrder add constraint FKClient_FK
     foreign key (UserId)
     references User (Id);

alter table ProductLabel add constraint FKTrads_FK
     foreign key (ProductId)
     references Product (Id);

alter table ProductLabel add constraint FKTrad_FK
     foreign key (LanguageId)
     references Language (Id);

alter table User add constraint FKLanguagePref_FK
     foreign key (LanguageId)
     references Language (Id);


-- Index Section
-- _____________ 

create unique index ID_OrderLine_IND
     on OrderLine (Id);

create index FKProductLine_IND
     on OrderLine (ProductId);

create index FKProductOrder_IND
     on OrderLine (ProductOrderId);

create unique index ID_Description_IND
     on Description (Id);

create index FKTradsDescription_IND
     on Description (ProductId);

create index FKTradDescription_IND
     on Description (LanguageId);

create unique index ID_Language_IND
     on Language (Id);

create unique index ID_LanguageLabel_IND
     on LanguageLabel (Id);

create index FKTradLanguage_IND
     on LanguageLabel (LanguageId);

create unique index ID_ProductOrder_IND
     on ProductOrder (Id);

create index FKClient_IND
     on ProductOrder (UserId);

create unique index ID_Product_IND
     on Product (Id);

create unique index ID_ProductLabel_IND
     on ProductLabel (Id);

create index FKTrads_IND
     on ProductLabel (ProductId);

create index FKTrad_IND
     on ProductLabel (LanguageId);

create unique index ID_User_IND
     on User (Id);

create unique index SID_User_IND
     on User (Email);

create index FKLanguagePref_IND
     on User (LanguageId);

