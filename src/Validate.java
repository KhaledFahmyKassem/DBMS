package eg.edu.alexu.csd.oop.dbms.cs23;
import java.io.IOException;
import java.util.regex.Pattern;


public class Validate {
	String query;
	public Validate(String query) {
		this.query = query.toLowerCase();
	}
	
	public Validate() {
	}
	
	boolean validateCreateDataBase(String query) {
		String temp = query.toLowerCase();
		return Pattern.matches("(create)\\s+(database)\\s+[a-zA-Z_]+[a-zA-Z0-9_]*\\s*;?", temp); 
	}
	
	boolean validateDropDataBase(String query) {
		String temp = query.toLowerCase();
		return Pattern.matches("(drop)\\s+(database)\\s+[a-zA-Z_]+[a-zA-Z0-9_]*\\s*;?", temp);
	}
	
	boolean validateCreateTable(String query) {
		String temp = query.toLowerCase();
		boolean b = Pattern.matches("(create)\\s+(table)\\s+[a-zA-Z_]+[a-zA-Z0-9_]*\\s*[(](\\s*[a-zA-Z_][a-zA-Z0-9_]*\\s+(int|varchar)\\s*[,]\\s*)+\\s*[a-zA-Z_][a-zA-Z0-9_]*\\s+(int|varchar)\\s*[)]\\s*;?\\s*", temp);
		return b;
	}
	
	boolean validateDropTable(String query) {
		String temp = query.toLowerCase();
		return Pattern.matches("(drop)\\s+(table)\\s+[a-zA-Z_][a-zA-Z0-9_]+\\s*;?\\s*", temp);
	}
	
	boolean ValidateSelectWithCondition(String query) {
		String temp = query.toLowerCase();
		return Pattern.matches("\\s*select\\s*[(]([a-zA-Z]+[a-zA-Z0-9 _]*\\s*[,]\\s*)*[a-zA-Z]+[a-zA-Z0-9 _]*[)]\\s*from\\s+[a-zA-Z]+[a-zA-Z0-9 _]*\\s+where\\s*[(]\\s*[a-zA-Z]+[a-zA-Z0-9_]*\\s*(=|<|>)\\s*[']\\s*[a-zA-Z0-9 _]+[']\\s*[)]\\s*;?\\s*", temp);
	}
	
	boolean validateSelect(String query){
		String temp = query.toLowerCase();
		return Pattern.matches("\\s*(select)(\\s+[(]?\\s*[a-zA-Z_][a-zA-Z0-9_]*,)*\\s*[a-zA-Z_][a-zA-Z0-9_]*[)]?\\s*(from)\\s+[a-zA-Z_][a-zA-Z0-9_]*\\s*;?\\s*", temp);
	}
	
	boolean validateUseDataBase(String query) {
		String temp = query.toLowerCase();
		return Pattern.matches("\\s*use\\s+database\\s+[a-zA-Z_][a-zA-Z0-9_]*\\s*;?\\s*", temp);
	}
	
	boolean validateSelectAll(String query) {
		String temp = query.toLowerCase();
		return Pattern.matches("\\s*select\\s*[*]\\s*from\\s+[a-zA-Z_][a-zA-Z0-9_]*\\s*;?\\s*", temp);
	}
	
	boolean validateDelete(String query){
		String temp = query.toLowerCase();
		return Pattern.matches("\\s*delete\\s+from\\s+[a-zA-Z]+[a-zA-Z0-9]*\\s*where\\s*[(]\\s*[a-zA-Z]+[a-zA-Z0-9]*\\s*(=|<|>)\\s*[']\\s*[a-zA-Z0-9 ]*\\s*[']\\s*[)]\\s*;?\\s*", temp);
	}
	
	boolean validateDeleteAll(String query) {
		String temp = query.toLowerCase();
		return Pattern.matches("delete\\s+[*]?\\s*from\\s+[a-zA-Z_][a-zA-Z0-9_]*\\s*;?\\s*", temp);
	}
	
	boolean validateUpdate(String query) {
		String temp = query.toLowerCase();
		return Pattern.matches("\\s*update\\s+[a-zA-Z_]+[a-zA-Z0-9]*\\s+set\\s*[(](\\s*[a-zA-Z_]+[a-zA-Z0-9]*\\s*=\\s*['][a-zA-Z0-9 ]*[']\\s*[,])*(\\s*[a-zA-Z_]+[a-zA-Z0-9]*\\s*=\\s*['][a-zA-Z0-9 ]*[']\\s*)[)]\\s*;?\\s*", temp);
	}
	
	
	boolean validateUpdateWithCondition(String query) {
		String temp = query.toLowerCase();
		return Pattern.matches("\\s*update\\s+[a-zA-Z_]+[a-zA-Z0-9]*\\s+set\\s*[(](\\s*[a-zA-Z_]+[a-zA-Z0-9]*\\s*=\\s*['][a-zA-Z0-9 ]*[']\\s*[,])*(\\s*[a-zA-Z_]+[a-zA-Z0-9]*\\s*=\\s*['][a-zA-Z0-9 ]*[']\\s*)[)]\\s*\\s*where\\s*[(]\\s*[a-zA-Z_]+[a-zA-Z0-9]*\\s*=\\s*[']\\s*[a-zA-Z0-9 ]*\\s*[']\\s*[)]\\s*;?\\s*", temp);
	}
	
	boolean validateInsert(String query){
		String temp = query.toLowerCase();
		return Pattern.matches("\\s*insert\\s+into\\s+[a-zA-Z_][a-zA-Z0-9_]*\\s*([(](\\s*[a-zA-Z_][a-zA-Z0-9_]*\\s*,)+\\s*[a-zA-Z_][a-zA-Z0-9_]*\\s*[)])(\\s*)values\\s*([(](\\s*[']\\s*[a-zA-Z0-9_ ]*\\s*[']\\s*,)*\\s*[']\\s*[a-zA-Z0-9_ ]*\\s*[']\\s*[)])\\s*;?\\s*", temp);
	}

	
	
	public static void main(String[] args) {
		Validate object = new Validate("");
//		System.out.println(object.validateCreateTable("Create table gamal (id int, name varchar, company varchar)"));
//		System.out.println(object.validateSelect("SELECT column_name,column_name FROM table_name WHERE column_name >= value;"));
//		System.out.println(object.validateDelete("DELETE FROM Customers where CustomerName = 'Alfreds Futterkiste'"));
//		System.out.println(object.validateUpdate("UPDATE table_name SET (column1='value1',column2='value2')"));
//		System.out.println(object.validateInsert("insert into gamal (id , state, salary) values('23', 'Egypt', '5000')"));
//		System.out.println(Pattern.matches("['](.+)[']", "'hfgh'"));
//		System.out.println(Pattern.matches("[a-z]", "a"));
//		System.out.println(Pattern.matches("gamal{2}", "gamalgamal"));
//		System.out.println("gamal".substring(2));
		
	}

}
