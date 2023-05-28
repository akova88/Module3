CREATE DATABASE QuanlySinhVien;
USE QuanlySinhVien;
create table Class(
ClassID int not null primary key auto_increment,
ClassName varchar(60) not null,
StarDate datetime not null,
Status bit
);
create table Student(
StudentID int not null primary key auto_increment,
StudentName varchar(30) not null,
Address varchar(50),
Phone varchar(20),
Status bit,
ClassID int not null,
foreign key (ClassID) references Class (ClassID)
);
CREATE TABLE Subject(
SubID int not null PRIMARY KEY AUTO_INCREMENT,
SubName VARCHAR(30) not NULL,
Credit TINYINT not null DEFAULT 1 CHECK (Credit >=1),
Status bit DEFAULT 1
);
CREATE TABLE Mark(
MarkID int not null PRIMARY KEY AUTO_INCREMENT,
SubID int not null,
StudentID int NOT NULL,
Mark FLOAT DEFAULT 0 CHECK (Mark BETWEEN 0 AND 100),
ExamTimes TINYINT DEFAULT 1,
UNIQUE (SubID, StudentID),
FOREIGN KEY (SubID) REFERENCES Subject (SubID),
FOREIGN KEY (StudentID) REFERENCES Student (StudentID)
);