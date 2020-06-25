-- Dumping database structure for DemoDatabase
drop database IF EXISTS DemoDatabase;
create database DemoDatabase;
use DemoDatabase;


-- Dumping structure for table DemoDatabase.Employee
DROP TABLE IF EXISTS Employee;
CREATE TABLE IF NOT EXISTS Employee (
  Emp_ID bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  Emp_Name varchar(50) DEFAULT NULL,
  Dept_Name varchar(50) DEFAULT NULL, 
  Address nvarchar(250) DEFAULT NULL,
  Email nvarchar(50) DEFAULT NULL,
  PhoneNo bigint(15) DEFAULT NULL,
  PRIMARY KEY (Emp_ID)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;



-- Dumping structure for table DemoDatabase.Department
DROP TABLE IF EXISTS Department;
CREATE TABLE IF NOT EXISTS Department (
  Dept_ID bigint(20) NOT NULL,
  Dept_Name varchar(50) DEFAULT NULL,
  PRIMARY KEY (Dept_ID),
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


Insert into Department values(1,'IT');
Insert into Department values(2,'Accounting');
Insert into Department values(3,'Research');
Insert into Department values(4,'Sales');
Insert into Department values(5,'Operations');

-- Dumping structure for table DemoDatabase.UserDetails
DROP TABLE IF EXISTS UserDetails;
CREATE TABLE IF NOT EXISTS UserDetails (
  User_ID bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  UserName nvarchar(50) DEFAULT NULL,
  Password nvarchar(15) DEFAULT NULL,
  Login_Time Datetime NOT NULL ,
  PRIMARY KEY (User_ID)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- use insert script like this CURDATE() function for current date insert.
INSERT INTO userdetails VALUES(NULL,'struts','struts123', CURDATE())