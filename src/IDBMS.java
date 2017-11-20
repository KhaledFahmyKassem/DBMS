package eg.edu.alexu.csd.oop.dbms.cs23;

import java.util.ArrayList;

import org.w3c.dom.Element;

public interface IDBMS {
/**
 * create database.
 * @param databaseName
 */
	public void createDatabase(String databaseName);
	
	/**
	 * create database.
	 * @param databaseName
	 */
	public void dropTable(String tableName);
	
	/**
	 * create a table.
	 * @param tableName.
	 */
	public void createTable(String tableName, ArrayList<String> colNames, ArrayList<String> typeCol);
	
	/**
	 * specifies the current database.
	 * @param dataBaseName
	 */
	public void useDataBase(String dataBaseName);
	
	/**
	 *	delete a database 
	 * @param path
	 */
	public void deleteDataBase (String dataBaseName);
	
   /**
	 *select from table with a where condition.  
	 */
	public ArrayList <ArrayList<String>> selectWithCondition(String tableName,ArrayList<String> columsRequired,
			String columnName, String value,String condition);

   /**
    * delete some data from table.
    * @param databaseName
    * @param tableName
    * @param columnRequired
    * @param value
    * @return
    */


   public void insertIntoTable(String tableName,
		ArrayList<String> tagsNamestobeInserted, ArrayList<String> values);


   public ArrayList<ArrayList<String>> update(String tableName,ArrayList<String> columnNames,
           ArrayList<String> newValues, String condColumn, String condColmValue, String operator);

ArrayList<Integer> deleteWithCondition(String tableName, String columnRequired,
		String value, String operator);
}