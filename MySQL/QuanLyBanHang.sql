CREATE DATABASE QuanLyBanHang;
USE QuanLyBanHang;
CREATE TABLE Customer(
cID int not null PRIMARY KEY AUTO_INCREMENT,
cName VARCHAR(20) not null,
cAge int 
);
CREATE TABLE `Order`(
oID int not null PRIMARY KEY AUTO_INCREMENT,
cID int not NULL,
oDate DATETIME,
oTotalPrice DOUBLE,
FOREIGN KEY (cID) REFERENCES Customer (cID)
);
CREATE TABLE Product(
pID int not null PRIMARY KEY AUTO_INCREMENT,
pName VARCHAR(30),
pPrice DOUBLE
);
CREATE TABLE OrderDetail(
oID int not NULL,
pID int not NULL,
FOREIGN KEY (oID) REFERENCES `Order` (oID),
FOREIGN KEY (pID) REFERENCES Product (pID)
);
ALTER TABLE quanlybanhang.orderdetail
ADD COLUMN odQTY int;
ALTER TABLE `quanlybanhang`.`orderdetail` 
ADD COLUMN `odQTY` INT NULL AFTER `pID`;