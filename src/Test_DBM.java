package eg.edu.alexu.csd.oop.dbms.cs23;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Test_DBM {
	Parser parse = new Parser();
	@Test
	public void test1(){
		
		
	}

	@org.junit.Test
	public void validation_class(){
		Validate va = new Validate();
		boolean temp = false;
		// test create database 
		temp = va.validateCreateDataBase("CREATE DATABASE dbname;");
		assertEquals(true, temp);
		temp = va.validateCreateDataBase("CREATEDATABASE dbname;");
		assertEquals(false, temp);
		temp = va.validateCreateDataBase("CREATE dbname;");
		assertEquals(false, temp);
		temp = va.validateCreateDataBase("CREATEDATABASE 5dbname;");
		assertEquals(false, temp);
		// test create table 
		temp = va.validateCreateTable("CREATE TABLE Persons(PersonID int,LastName varchar,FirstName varchar,Address varchar,City varchar);");
		assertEquals(true, temp);
		temp = va.validateCreateTable("CREATE TABLE Persons(PersonID int,LastName varchar FirstName varchar,Address varchar,City varchar);");
		assertEquals(false, temp);
		temp = va.validateCreateTable("CREATE TABLE Persons PersonID int,LastName varchar,FirstName varchar,Address varchar,City varchar);");
		assertEquals(false, temp);
		temp = va.validateCreateTable("CREATE  Persons (PersonID int,LastName varchar,FirstName varchar,Address varchar,City varchar);");
		assertEquals(false, temp);
		// test Delete all
		temp = va.validateDeleteAll("DELETE * FROM table_name;");
		assertEquals(true, temp);
		temp = va.validateDeleteAll("DELETE FROM table_name;");
		assertEquals(true, temp);
		temp = va.validateDeleteAll("DELETE table_name;");
		assertEquals(false, temp);
		temp = va.validateDeleteAll("DELETEFROM table_name;");
		assertEquals(false, temp);
		// test delete with condition 
		temp = va.validateDelete("DELETE FROM Customers WHERE (CustomerName='Alfreds Futterkiste');");
		assertEquals(true, temp);
		temp = va.validateDelete("DELETE FROM Customers WHERE CustomerName=Alfreds Futterkiste';");
		assertEquals(false, temp);
		temp = va.validateDelete("DELETE FROM Customers WHERE CustomerName 'Alfreds Futterkiste';");
		assertEquals(false, temp);
		// test drop dataBase
		temp = va.validateDropDataBase("DROP DATABASE database_name;");
		assertEquals(true, temp);
		temp = va.validateDropDataBase("DROP DATABASEdatabase_name;");
		assertEquals(false, temp);
		temp = va.validateDropDataBase("DROP DATABASE 9database_name;");
		assertEquals(false, temp);
		// test drop dataBase
		temp = va.validateDropTable("DROP TABLE table_name;");
		assertEquals(true, temp);
		temp = va.validateDropTable("DROP TABLE 9table_name;");
		assertEquals(false, temp);
		temp = va.validateDropTable("DROP TABLEtable_name;");
		assertEquals(false, temp);
		temp = va.validateDropTable("DROP table_name;");
		assertEquals(false, temp);
		//test insert with condition 
		temp = va.validateInsert("INSERT INTO table_name (column1,column2,column3)VALUES ('value1','value2','value3');");
		assertEquals(true, temp);
		temp = va.validateInsert("INSERT INTO table_name column1,column2,column3)VALUES ('value1','value2','value3');");
		assertEquals(false, temp);
		temp = va.validateInsert("INSERT INTO table_name (column1,column2,column3)  ('value1','value2','value3');");
		assertEquals(false, temp);
		temp = va.validateInsert("INSERT INTO table_name (column1,column2,column3)VALUES (value1','value2','value3');");
		assertEquals(false, temp);
		temp = va.validateInsert("INSERT INTO table_name (column1,column2,column3)VALUES 'value1','value2','value3');");
		assertEquals(false, temp);
		temp = va.validateInsert("INSERT INTO table_name(column1 , column2 , column3 ) VALUES ('value1' , 'value2' , 'value3' );");
		assertEquals(true, temp);
		// test select all
		temp = va.validateSelectAll("SELECT * FROM table_name;");
		assertEquals(true, temp);
		temp = va.validateSelectAll("SELECT FROM table_name;");
		assertEquals(false, temp);
		temp = va.validateSelectAll("SELECT  table_name;");
		assertEquals(false, temp);
		temp = va.validateSelectAll("SELECT * FROMtable_name;");
		assertEquals(false, temp);
		// test select 
		temp = va.validateSelect("SELECT column_name,column_name FROM table_name;");
		assertEquals(true, temp);
		temp = va.validateSelect("SELECT column_name column_name FROM table_name;");
		assertEquals(false, temp);
		temp = va.validateSelect("SELECT column_name,column_name FROMtable_name;");
		assertEquals(false, temp);
		temp = va.validateSelect("SELECT column_name,column_name  table_name;");
		assertEquals(false, temp);
		// test select with condition
		temp = va.ValidateSelectWithCondition("SELECT (column_name,column_name) FROM table_name WHERE (column_name = 'value');");
		assertEquals(true, temp);
		temp = va.ValidateSelectWithCondition("SELECT (column_name column_name) FROM table_nameWHERE (column_name = 'value');");
		assertEquals(false, temp);
		temp = va.ValidateSelectWithCondition("SELECT column_name,column_name) FROM table_name WHERE (column_name = 'value');");
		assertEquals(false, temp);
		temp = va.ValidateSelectWithCondition("SELECT (column_name,column_name)  table_name WHERE (column_name = 'value');");
		assertEquals(false, temp);
		temp = va.ValidateSelectWithCondition("SELECT (column_name,column_name) FROM table_name WHERE (column_name < 'value');");
		assertEquals(true, temp);
		temp = va.ValidateSelectWithCondition("SELECT (column_name,column_name) FROM table_name WHERE (column_name = value');");
		assertEquals(false, temp);
		
		// test use data base
		temp = va.validateUseDataBase("use database database_name;");
		assertEquals(true, temp);
		temp = va.validateUseDataBase("use database_name;");
		assertEquals(false, temp);
		temp = va.validateUseDataBase("use databasedatabase_name;");
		assertEquals(false, temp);
		// test update all
		temp = va.validateUpdate("UPDATE Customers SET (ContactName='Alfred Schmidt', City='Hamburg');");
		assertEquals(true, temp);
		temp = va.validateUpdate("UPDATE Customers SET (ContactName'Alfred Schmidt', City='Hamburg');");
		assertEquals(false, temp);
		temp = va.validateUpdate("UPDATE Customers SET (ContactName='Alfred Schmidt' City='Hamburg');");
		assertEquals(false, temp);
		temp = va.validateUpdate("UPDATE Customers (ContactName='Alfred Schmidt', City='Hamburg');");
		assertEquals(false, temp);
		// test update with condition
		temp = va.validateUpdateWithCondition("UPDATE Customers SET (ContactName='Alfred Schmidt', City='Hamburg') WHERE (CustomerName='Alfreds Futterkiste');");
		assertEquals(true, temp);
		temp = va.validateUpdateWithCondition("UPDATE Customers SET (ContactName=Alfred Schmidt', City='Hamburg') WHERE (CustomerName='Alfreds Futterkiste');");
		assertEquals(false, temp);
		temp = va.validateUpdateWithCondition("UPDATE Customers SET (ContactName='Alfred Schmidt' City='Hamburg') WHERE (CustomerName='Alfreds Futterkiste');");
		assertEquals(false, temp);
		temp = va.validateUpdateWithCondition("UPDATE Customers SET (ContactName='Alfred Schmidt', City'Hamburg') WHERE (CustomerName='Alfreds Futterkiste');");
		assertEquals(false, temp);
		temp = va.validateUpdateWithCondition("UPDATE Customers SET ContactName='Alfred Schmidt', City='Hamburg') WHERE (CustomerName='Alfreds Futterkiste');");
		assertEquals(false, temp);
		temp = va.validateUpdateWithCondition("UPDATE Customers SET (ContactName='Alfred Schmidt', City='Hamburg' WHERE (CustomerName='Alfreds Futterkiste');");
		assertEquals(false, temp);
		temp = va.validateUpdateWithCondition("UPDATE Customers SET (ContactName='Alfred Schmidt', City='Hamburg')  (CustomerName='Alfreds Futterkiste');");
		assertEquals(false, temp);
		temp = va.validateUpdateWithCondition("UPDATE Customers SET (ContactName='Alfred Schmidt' ,City='Hamburg') WHERE CustomerName='Alfreds Futterkiste');");
		assertEquals(false, temp);
		temp = va.validateUpdateWithCondition("UPDATE Customers SET (ContactName='Alfred Schmidt' ,City='Hamburg') WHERE (CustomerName'Alfreds Futterkiste');");
		assertEquals(false, temp);
		temp = va.validateUpdateWithCondition("UPDATE Customers SET (ContactName='Alfred Schmidt', City='Hamburg') WHERE (CustomerName=Alfreds Futterkiste');");
		assertEquals(false, temp);
		temp = va.validateUpdateWithCondition("UPDATE Customers SET (ContactName='Alfred Schmidt' ,City='Hamburg') WHERE (CustomerName='Alfreds Futterkiste';");
		assertEquals(false, temp);
		temp = va.validateUpdateWithCondition("UPDATE Customers SET (ContactName='Alfred Schmidt' City='Hamburg') WHERE (CustomerName='Alfreds Futterkiste);");
		assertEquals(false, temp);
	}
	
}
