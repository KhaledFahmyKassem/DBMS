package eg.edu.alexu.csd.oop.dbms.cs23;

import java.util.ArrayList;

public class Print {
	private int get_maxlenght(ArrayList<ArrayList<String>> table){
		int len = 0;
		for (int i = 0 ;i <table.size();i++){
			for (int j =0; j<table.get(i).size();j++){
				if (table.get(i).get(j).length() > len)
					len = table.get(i).get(j).length();
			}
		}
		return len;
		
	}
	private String char_number(int a){
		String ouput = "";
		for (int i = 0 ; i <a+3 ;i++){
			ouput +="-"; 
		}
		return ouput;
	}
	private String spaces_number(int a){
		String ouput = "";
		for (int i = 0 ; i <a+3 ;i++){
			ouput +=" "; 
		}
		ouput += "|";
		return ouput;
	}
	public void printTable(ArrayList<ArrayList<String>> table){
		
		for (int i = 1; i < table.size();i++){
			for (int j = 0 ;j<table.get(i).size();j++){
				
			}
		}
		int len = get_maxlenght(table);
		String format = "|";
		String temp = "+";
		for (int i = 0 ;i <table.get(0).size();i++){
			temp += char_number(len)+"+";
		}
		temp += "\n";
		temp += "|";
		for (int i = 0 ;i <table.get(0).size();i++){
			temp += table.get(0).get(i)+spaces_number(len - table.get(0).get(i).length());
		}
		temp += "\n";
		temp += "+";
		for (int i = 0 ;i <table.get(0).size();i++){
			temp += char_number(len)+"+";
		}
		temp += "\n";
		for (int i = 0 ;i < (table.get(1).size());i++){
			temp += "|";
			for (int j = 1; j < (table.size());j++){
				temp += table.get(j).get(i)+spaces_number(len - table.get(j).get(i).length());
			}
			temp += "\n";
		}
		temp += "+";
		for (int i = 0 ;i <table.get(0).size();i++){
			temp += char_number(len)+"+";
		}
		temp += "\n";
		System.out.println(temp);
	}

}
