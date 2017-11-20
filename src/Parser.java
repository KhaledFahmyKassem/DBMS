package eg.edu.alexu.csd.oop.dbms.cs23;

import java.util.ArrayList;

public class Parser {
	Validate validate = new Validate();
	DataBase app = new DataBase();
	public void createDataBase(String query) {
		String temp = query.toLowerCase();
		String dataBaseName = "";
		if(temp.contains(";")) {
			dataBaseName = query.substring(temp.indexOf("database") + 8, temp.indexOf(";")).trim();
		} else {
			dataBaseName = query.substring(temp.indexOf("database") + 8, temp.length()).trim();
		}
			app.createDatabase(dataBaseName);
	}
	public void dropDataBase(String query) {
		String temp = query.toLowerCase();
		String dataBaseName = "";
		if(temp.contains(";")) {
			dataBaseName = query.substring(temp.indexOf("database") + 8, temp.indexOf(";")).trim();
		} else {
			dataBaseName = query.substring(temp.indexOf("database") + 8, temp.length()).trim();
		}
			app.deleteDataBase(dataBaseName);
	}
	
	public void useDataBase(String query) {
		String temp = query.toLowerCase();
		String dataBaseName = "";
		if(temp.contains(";")) {
			dataBaseName = query.substring(temp.indexOf("database") + 8, temp.indexOf(";")).trim();
		} else {
			dataBaseName = query.substring(temp.indexOf("database") + 8, temp.length()).trim();
		}
			app.useDataBase(dataBaseName);
	}
	
	public void update(String query) {
		String con = "";
		String temp = query.toLowerCase();
		ArrayList<String> colNames = new ArrayList<>();
		ArrayList<String> newValues = new ArrayList<>();
		String tableName = query.substring(temp.indexOf("update") + 6, temp.indexOf("set")).trim();
		String change = query.substring(temp.indexOf("(") + 1, temp.indexOf(")")).trim();
		String arr[] = change.split("=|,");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
			if(i % 2 == 0) {
				colNames.add(arr[i]);
			}
			else {
				newValues.add(arr[i]);
			}
		}
		////////////call here///////////////
		app.update(tableName, colNames, newValues, null, null, null);
	}
	public void createTable(String query) {
		query = query.replaceAll("\\s+", " ");
		String temp = query.toLowerCase();
		String tableName = query.substring(temp.indexOf("create table") + 12, temp.indexOf("("));
		tableName = tableName.trim();
		String par = query.substring(temp.indexOf("(") + 1, temp.indexOf(")") ).trim();
		String arr[] = par.split(",");
		for(int i = 0; i < arr.length; i ++) {
			arr[i] = arr[i].trim();
		}
		ArrayList<String> colNames = new ArrayList<>();
		ArrayList<String> types = new ArrayList<>();
		for(int i = 0; i < arr.length; i ++) {
			colNames.add(arr[i].substring(0, arr[i].indexOf(" ")));
			types.add(arr[i].substring( arr[i].indexOf(" ") + 1, arr[i].length()));
		}
		app.createTable(tableName, colNames, types);
	}
	
	public void dropTable(String query) {
		String arr[] = query.split("\\s+");
		app.dropTable(arr[2].trim());
	}
	
	      
	public void select(String query) {
		String temp = query.toLowerCase();
		String tableName = "";
		if(temp.contains(";")) {
			tableName = query.substring(temp.indexOf("from") + 4, temp.indexOf(";")).trim();
		}
		else {
			tableName = query.substring(temp.indexOf("from") + 4, temp.length()).trim();
		}
		temp.replaceAll("\\s+", " ");
		String cols = query.substring(temp.indexOf("select") + 6, temp.indexOf("from"));
		String arr[] = cols.split(",");
		ArrayList<String> colNames = new ArrayList<>();
		for(int i = 0; i < arr.length; i ++) {
			//arr[i] = arr[i].trim();
			arr[i].replaceAll("(|)", "");
			colNames.add(arr[i].trim());
		}
		////////////////////////call here///////////////////
		app.selectCol(tableName, colNames);
	}
	
	public void selectAll(String query) {
		String temp = query.toLowerCase();
		String tableName = "";
		if(temp.contains(";")) {
			tableName = query.substring(temp.indexOf("from") + 4, temp.indexOf(";")).trim();
		}
		else {
			tableName = query.substring(temp.indexOf("from") + 4, temp.length()).trim();
		}
		/////////////////////////call here//////////////////////
		app.selectAll(tableName);
	}

	public void selectWithCondition(String query) {
		String temp = query.toLowerCase();
		String tableName = "";
		String colName = "";
		String value = "";
		String operator = "";
		tableName = query.substring(temp.indexOf("from") + 4, temp.indexOf("where")).trim();
		temp.replaceAll("\\s+", " ");
		String cols = query.substring(temp.indexOf("(") + 1, temp.indexOf(")"));
		String arr[] = cols.split(",");
		ArrayList<String> colNames = new ArrayList<>();
		for(int i = 0; i < arr.length; i ++) {
			//arr[i] = arr[i].trim();
			colNames.add(arr[i].trim());
		}
		ArrayList<String> res = checkCondition(query);
		colName = res.get(0);
		value = res.get(1);
		operator = res.get(2);
		////////////call here//////////////////
		app.selectWithCondition(tableName, colNames, colName, value, operator);
	 }
	public void insert(String query) {
		String temp = query.toLowerCase();
		String tableName = query.substring(temp.indexOf("into") + 4, temp.indexOf("(")).trim();
		String cols = query.substring(temp.indexOf("(")+1, temp.indexOf(")"));
		String values = query.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));
		String colArr[] = cols.split(",");
		String valArr[] = values.split(",");
		if(colArr.length != valArr.length) {
			System.out.println("Not consistent number of values !!!!");
			return ;
		}
		ArrayList<String> colNames = new ArrayList<>();
		ArrayList<String>  valList = new ArrayList<>();
		for(int i = 0; i < colArr.length; i ++) {
			colNames.add(colArr[i].trim());
			valList.add(valArr[i].trim());
		}
		////////////////////call here////////////////
		app.insertIntoTable(tableName, colNames, valList);
	}
	public void updateWithCondition(String query) {
		String con = "";
		String temp = query.toLowerCase();
		ArrayList<String> colNames = new ArrayList<>();
		ArrayList<String> newValues = new ArrayList<>();
		String tableName = query.substring(temp.indexOf("update") + 6, temp.indexOf("set")).trim();
		String change = query.substring(temp.indexOf("(") + 1, temp.indexOf(")")).trim();
		String arr[] = change.split("=|,");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
			if(i % 2 == 0) {
				colNames.add(arr[i]);
			}
			else {
				newValues.add(arr[i]);
			}
		}
		ArrayList<String> condition = checkCondition(query);
		String col = condition.get(0);
		String val = condition.get(1);
		String operator = condition.get(2);
		////////////////call here/////////////////
		app.update(tableName, colNames, newValues, col, val, operator);
		
	}
	
	public void deleteWithCondition(String query) {
		String temp = query.toLowerCase();
		String tableName = query.substring(temp.indexOf("from") + 4, temp.indexOf("where")).trim();
		String con = query.substring(temp.indexOf("(") + 1, temp.indexOf(")")).trim();
		String []arr = con.split("=|<|>");
		String operator = "";
		if(con.contains("=")) {
			operator = "=";
		}
		else if(con.contains("<")) {
			operator = "<";
		}
		else {
			operator = ">";
		}
		String col = arr[0].trim();
		String val = arr[1].trim();
		/////////////////////////call here////////////////
		app.deleteWithCondition(tableName, col, val, operator);
	}
	
	
	public void deleteAll(String query) {
		String temp = query.toLowerCase();
		String tableName = "";
		if(temp.contains(";")) {
			tableName = query.substring(temp.indexOf("from") + 4, temp.indexOf(";")).trim();
		}
		else {
			tableName = query.substring(temp.indexOf("from") + 4, temp.length());
		}
		/////////////////////////////call here////////////////
		app.deleteAll(tableName);
	}
	
	private ArrayList<String> checkCondition(String query) {
		String con = "";
		ArrayList<String> res = new ArrayList<>();
		String temp = query.toLowerCase();
		con = query.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));
		String arr[] = con.split("=|<|>");
		res.add(arr[0].trim());
		res.add(arr[1].trim());
		if(con.contains("=")) {
			res.add("=");
		}
		else if (con.contains(">")) {
			res.add(">");
		}
		else {
			res.add("<");
		}
		return res;
	}
	
	

} 
