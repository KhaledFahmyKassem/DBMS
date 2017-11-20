package eg.edu.alexu.csd.oop.dbms.cs23;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String temp = "";
		Scanner input = new Scanner(System.in);
		Validate isValid = new Validate();
		Parser parse = new Parser();
		while(true) {
			temp = input.nextLine();
			if(isValid.validateCreateDataBase(temp)){
				parse.createDataBase(temp);
			}
			else if(isValid.validateDropDataBase(temp)) {
				parse.dropDataBase(temp);
			}
			else if(isValid.validateCreateTable(temp)) {
				parse.createTable(temp);
			}
			else if(isValid.validateUseDataBase(temp)) {
				parse.useDataBase(temp);
			}
			else if(isValid.validateInsert(temp)) {
				parse.insert(temp);
			}
			else if(isValid.validateDropTable(temp)) {
				parse.dropTable(temp);
			}
			else if(isValid.validateUpdateWithCondition(temp)) {
				parse.updateWithCondition(temp);
			}
			else if(isValid.ValidateSelectWithCondition(temp)) {
				parse.selectWithCondition(temp);
			}
			else if(isValid.validateUpdate(temp)) {
				parse.update(temp);
			}
			else if(isValid.validateDelete(temp)) {
				parse.deleteWithCondition(temp);	
			}
			else if(isValid.validateDeleteAll(temp)) {

				parse.deleteAll(temp);
				
			}
			else if(isValid.validateSelect(temp)) {
				parse.select(temp);
			}
			else if(isValid.validateSelectAll(temp)) {
				parse.selectAll(temp);
			}
			else
				System.out.println("Invalid query");				
			}
	}

}
