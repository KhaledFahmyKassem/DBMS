package eg.edu.alexu.csd.oop.dbms.cs23;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DataBase implements IDBMS {
	private String curDataBaseName = null;
	Print pr = new Print();


	@Override
	public void createDatabase(String databaseName) {
		File folder = new File(databaseName);
		if (!folder.exists()) {
			try {
				folder.mkdir();
			} catch (SecurityException se) {

			}
		}

	}
	@Override
	public void deleteDataBase(String dataBaseName) {
		File folder = new File(dataBaseName);
		if (!folder.exists()) {
			System.out.println("File not exist");
		} else {
			if (folder.list().length == 0) {
				folder.delete();
			} else {
				String[] files = folder.list();
				for (int i = 0; i < files.length; i++) {
					String temp = files[i];
					File fileDelete = new File(dataBaseName + "\\" + temp);
					fileDelete.delete();
				}
				folder.delete();
			}
		}

	}

	@Override
	public ArrayList<ArrayList<String>> selectWithCondition(String tableName, ArrayList<String> columsRequired,
			String columnName, String value, String condition) {
		int index = 0;
		ArrayList<String> zz = new ArrayList<>();
		for (int i = 0; i < getColNames(tableName).length; i++) {
			zz.add(getColNames(tableName)[i]);
		}
		ArrayList<ArrayList<String>> selectArr = arr(zz, tableName);
		if(!validateColRequired(zz, columsRequired)||!(checkColCond(zz, columnName))){
			System.out.println("invalid input");
			return selectArr;}
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < selectArr.get(0).size(); i++) {
			if (selectArr.get(0).get(i).equals(columnName)) {
				index = i + 1;
				break;
			}
		}
		for (int i = 0; i < selectArr.get(index).size(); i++) {
			if (condition == "=") {
				if (selectArr.get(index).get(i).equals(value)) {

					indexes.add(i);
				}
			} else if (condition == ">") {
				if (selectArr.get(index).get(i).compareTo(value) > 0) {
					indexes.add(i);
				}
			} else if (condition == "<") {
				if (selectArr.get(index).get(i).compareTo(value) < 0) {
					indexes.add(i);
				}
			}
		}
		ArrayList<String> cell;
		ArrayList<ArrayList<String>> cells = new ArrayList<ArrayList<String>>();
		cells.add(columsRequired);

		for (int i = 0; i < columsRequired.size(); i++) {
			int columnreq = selectArr.get(0).indexOf(columsRequired.get(i)) + 1;
			cell = new ArrayList<String>();
			for (int j = 0; j < indexes.size(); j++) {
				cell.add(selectArr.get(columnreq).get(indexes.get(j)));
			}
			cells.add(cell);
		}
		pr.printTable(cells);
		return cells;
	}


	public ArrayList<ArrayList<String>> update(String tableName, ArrayList<String> columnNames,
			ArrayList<String> newValues, String condColumn, String condColmValue, String operator) {
		ArrayList<String> zz = new ArrayList<>();
		for (int i = 0; i < getColNames(tableName).length; i++) {
			zz.add(getColNames(tableName)[i]);
		}
		ArrayList<ArrayList<String>> updateArr = arr(zz, tableName);
		if (condColumn == null && condColmValue == null && operator == null) {
			for (int i = 0; i < columnNames.size(); i++) {
				int column = updateArr.get(0).indexOf(columnNames.get(i)) + 1;
				for (int j = 0; j < updateArr.get(column).size(); j++) {
					updateArr.get(column).set(j, newValues.get(i));
				}
			}
			ArrayList<String> type = getAttribuöte(tableName);
			dropTable(tableName);
			createTable(tableName, zz, type);
			drowTableInXml(tableName, updateArr);
			pr.printTable(updateArr);
			return updateArr;
		} else {
			int index = 0;
			
			if(!validateColRequired(zz, columnNames)||!(checkColCond(zz, condColumn))){
				System.out.println("invalid input");
				return null;}
			for (int i = 0; i < updateArr.get(0).size(); i++) {
				if (updateArr.get(0).get(i).equals(condColumn)) {
					index = i + 1;
					break;
				}
			}

			ArrayList<Integer> rowNumbersToBoUpdated = new ArrayList<Integer>();
			for (int i = 0; i < updateArr.get(index).size(); i++) {
				if (operator == "=") {
					if (updateArr.get(index).get(i).equals(condColmValue)) {
						rowNumbersToBoUpdated.add(i);
					}
				} else if (operator == ">") {
					if (updateArr.get(index).get(i).compareTo(condColmValue) > 0) {
						rowNumbersToBoUpdated.add(i);
					}
				} else if (operator == "<") {
					if (updateArr.get(index).get(i).compareTo(condColmValue) < 0) {
						rowNumbersToBoUpdated.add(i);
					}
				}

			}
			for (int i = 0; i < columnNames.size(); i++) {

				int column = updateArr.get(0).indexOf(columnNames.get(i)) + 1;

				for (int k = 0; k < rowNumbersToBoUpdated.size(); k++) {

					updateArr.get(column).set(rowNumbersToBoUpdated.get(k), newValues.get(i));
				}
			}
			ArrayList<String> type = getAttribuöte(tableName);
			dropTable(tableName);
			createTable(tableName, zz, type);
			drowTableInXml(tableName, updateArr);
			pr.printTable(updateArr);
			return updateArr;
		}
	}

	public ArrayList<ArrayList<String>> selectCol(String tableName, ArrayList<String> col) {
		ArrayList<String> zz = new ArrayList<>();
		for (int i = 0; i < getColNames(tableName).length; i++) {
			zz.add(getColNames(tableName)[i]);
		}
		ArrayList<ArrayList<String>> selectArr = arr(zz, tableName);
		if(!validateColRequired(zz, col)){
			System.out.println("invalid input");
			return selectArr;}
		ArrayList<ArrayList<String>> cells = new ArrayList<ArrayList<String>>();
		cells.add(col);
		for (int i = 0; i < col.size(); i++) {
			if (selectArr.get(0).contains(col.get(i))) {
				int index = selectArr.get(0).indexOf(col.get(i)) + 1;
				cells.add(selectArr.get(index));
			}
		}
		pr.printTable(cells);
		return cells;
	}
	@Override
	public void useDataBase(String dataBaseName) {
		this.curDataBaseName = dataBaseName;

	}
	@Override
	public void dropTable(String tableName) {
		File file = new File(curDataBaseName + "\\" + tableName + ".xml");
		file.delete();

	}

	@Override
	public void createTable(String tableName, ArrayList<String> colNames, ArrayList<String> typeCol) {
		try {
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement = doc.createElement(tableName);
			String str = "";
			for (int i = 0; i < colNames.size(); i++) {
				str += colNames.get(i);
				if (i != colNames.size() - 1)
					str += " ";
			}
			rootElement.setAttribute("Names", str);
			doc.appendChild(rootElement);
			for (int i = 0; i < colNames.size(); i++) {
				Element x = doc.createElement(colNames.get(i));
				x.setAttribute("type", typeCol.get(i));
				rootElement.appendChild(x);
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(curDataBaseName + "\\" + tableName + ".xml"));
				transformer.transform(source, result);
			}

		} catch (Exception e) {
		}

	}
	private String[] getColNames(String tableName) {
		String[] strs = null;
		try {
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(curDataBaseName + "\\" + tableName + ".xml");
			NodeList nodeList = doc.getElementsByTagName(tableName);
			Node node = nodeList.item(0);
			Element element = (Element) node;
			String str = element.getAttribute("Names");
			strs = str.split("\\s+");

		} catch (Exception e) {
		}

		return strs;
	}

	private ArrayList<String> getAttribuöte(String nameTable) {
		ArrayList<String> col = new ArrayList<>();
		ArrayList<String> attribute = new ArrayList<>();
		for (int i = 0; i < getColNames(nameTable).length; i++) {
			col.add(getColNames(nameTable)[i]);
		}
		try {
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(curDataBaseName + "\\" + nameTable + ".xml");
			// NodeList x=(NodeList)zz;
			for (int i = 0; i < col.size(); i++) {
				NodeList nodeList = doc.getElementsByTagName(col.get(i));
				Node node = nodeList.item(0);
				Element element = (Element) node;
				attribute.add(element.getAttribute("type"));
			}
		} catch (Exception e) {
		}
		return attribute;
	}

	public ArrayList<ArrayList<String>> arr(ArrayList<String> col, String nameTable) {
		ArrayList<ArrayList<String>> x = loadFromFile(col, nameTable);

		ArrayList<ArrayList<String>> arr = new ArrayList<>();
		ArrayList<String> sub = new ArrayList<>();
		arr.add(x.get(0));
		for (int i = 1; i < x.size(); i++) {
			for (int j = 1; j < x.get(i).size(); j = j + 2) {
				sub.add(x.get(i).get(j));
			}
			arr.add(sub);
			sub = new ArrayList<>();
		}
		return arr;
	}

	@Override
	public void insertIntoTable(String tableName, ArrayList<String> tagsNamestobeInserted, ArrayList<String> values) {
		boolean check = false;
		
		ArrayList<String> zz = new ArrayList<>();
		for (int i = 0; i < getColNames(tableName).length; i++) {
			zz.add(getColNames(tableName)[i]);
		}
		
		ArrayList<ArrayList<String>> insertToArr = arr(zz, tableName);
		if (tagsNamestobeInserted == null && values.size() == insertToArr.get(0).size()) {
			for (int i = 0; i < insertToArr.get(0).size(); i++) {
				insertToArr.get(i + 1).add(values.get(i));
			}

		} else if (tagsNamestobeInserted.size() == values.size()) {
			if(!validateColRequired(zz, tagsNamestobeInserted)){
				System.out.println("invalid input");
				return ;}
			for (int i = 0; i < insertToArr.get(0).size(); i++) {
				check = false;
				for (int j = 0; j < tagsNamestobeInserted.size(); j++) {
					if (insertToArr.get(0).get(i).equals((tagsNamestobeInserted.get(j)))) {
						insertToArr.get(i + 1).add(values.get(j));
						check = true;
						break;
					}
				}
				if (check == false) {
					insertToArr.get(i + 1).add("null");
				}
			}

		}

		pr.printTable(insertToArr);

		ArrayList<String> type = getAttribuöte(tableName);
		dropTable(tableName);
		createTable(tableName, zz, type);
		drowTableInXml(tableName, insertToArr);

	}
	public void drowTableInXml(String tableName, ArrayList<ArrayList<String>> data) {
		ArrayList<String> col = new ArrayList<>();
		for (int i = 0; i < getColNames(tableName).length; i++) {
			col.add(getColNames(tableName)[i]);
		}
		try {
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse((curDataBaseName + "\\" + tableName + ".xml"));
			for (int i = 0; i < col.size(); i++) {
				for (int j = 0; j < data.get(1).size(); j++) {
					Element sub = doc.createElement("row" + j);
					sub.appendChild(doc.createTextNode(data.get(i + 1).get(j)));
					doc.getElementsByTagName(col.get(i)).item(0).appendChild(sub);
				}
			}
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(curDataBaseName + "\\" + tableName + ".xml");
			transformer.transform(source, result);
		} catch (Exception e) {
		}

	}

	public ArrayList<ArrayList<String>> loadFromFile(ArrayList<String> col, String nameTable) {
		try {
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(curDataBaseName + "\\" + nameTable + ".xml");
			ArrayList<ArrayList<String>> strr = new ArrayList<ArrayList<String>>();
			strr.add(new ArrayList<String>());
			for (int i = 0; i < col.size(); i++) {
				strr.get(0).add(col.get(i));
				NodeList nodeList = doc.getElementsByTagName(col.get(i));
				Node node = nodeList.item(0);
				Element element = (Element) node;
				NodeList nodess = element.getChildNodes();
				strr.add(new ArrayList<String>());
				for (int j = 0; j < nodess.getLength(); j++) {
					strr.get(i + 1).add(nodess.item(j).getTextContent());
				}
			}
			return strr;

		} catch (Exception e) {

		}
		return null;
	}

	public ArrayList<String> getCols(String tableName) {
		ArrayList<String> cols = new ArrayList<>();
		File file = new File(curDataBaseName + "\\" + tableName + ".dtd");
		BufferedReader br = null;
		try {

			if (!file.exists()) {
				System.out.println("Table not existed !!!");
			}
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			br.readLine();
			String names = br.readLine();
			names = names.substring(1, names.length() - 2);
			String[] arr = names.split(",");
			for (int i = 0; i < arr.length; i++) {
				cols.add(arr[i]);
			}
		} catch (Exception e) {

		} finally {
			try {
				br.close();
			} catch (Exception r) {

			}
		}

		return cols;
	}

	public void deleteAll(String tableName) {
		ArrayList<String> zz = new ArrayList<>();
		for (int i = 0; i < getColNames(tableName).length; i++) {
			zz.add(getColNames(tableName)[i]);
		}
		ArrayList<String> type = getAttribuöte(tableName);
		dropTable(tableName);
		createTable(tableName, zz, type);
	}

	public void selectAll(String tableName) {
		ArrayList<String> zz = new ArrayList<>();
		for (int i = 0; i < getColNames(tableName).length; i++) {
			zz.add(getColNames(tableName)[i]);
		}
		ArrayList<ArrayList<String>> selectArr = arr(zz, tableName);
		pr.printTable(selectArr);
	}

	@Override
	public ArrayList<Integer> deleteWithCondition(String tableName, String columnRequired, String value,
			String operator) {
		ArrayList<String> zz = new ArrayList<>();
		for (int i = 0; i < getColNames(tableName).length; i++) {
			zz.add(getColNames(tableName)[i]);
		}
		ArrayList<ArrayList<String>> updateArr = arr(zz, tableName);
		int index = 0;
		if (!checkColCond(zz, columnRequired)){
			System.out.println("invalid querey");
			return null;
			
		}
		ArrayList<Integer> indexes = new ArrayList<Integer>();

		for (int i = 0; i < updateArr.get(0).size(); i++) {
			if (updateArr.get(0).get(i).equals(columnRequired)) {
				index = i + 1;
				break;
			}
		}

		ArrayList<Integer> rowNumbersToBoUpdated = new ArrayList<Integer>();

		for (int i = 0; i < updateArr.get(index).size(); i++) {
			if (operator == "=") {
				if (updateArr.get(index).get(i).equals(value)) {

					rowNumbersToBoUpdated.add(i);
				}
			} else if (operator == ">") {
				if (updateArr.get(index).get(i).compareTo(value) > 0) {
					rowNumbersToBoUpdated.add(i);
				}
			} else if (operator == "<") {
				if (updateArr.get(index).get(i).compareTo(value) < 0) {
					rowNumbersToBoUpdated.add(i);
				}
			}
		}
		for (int i = 1; i < updateArr.size(); i++) {
			int cnt = 0;
			for (int j = 0; j < rowNumbersToBoUpdated.size(); j++) {
				updateArr.get(i).remove(rowNumbersToBoUpdated.get(j).intValue() - cnt);
				cnt++;
			}
		}

		ArrayList<String> type = getAttribuöte(tableName);
		dropTable(tableName);
		createTable(tableName, zz, type);
		drowTableInXml(tableName, updateArr);
		pr.printTable(updateArr);
		return indexes;
	}

	private boolean validateColRequired(ArrayList<String> colNames, ArrayList<String> colrequired) {
		boolean exit = false;
		for (int i = 0; i < colrequired.size(); i++) {
			exit = false;
			for (int j = 0; j < colNames.size(); j++) {

				if (colrequired.get(i).equals(colNames.get(j))) {
					exit = true;
				}
			}
			if(exit==false){break;}
		}
		return exit;
	}
	private boolean checkColCond(ArrayList<String> colNames, String colrequired) {
	
		return colNames.contains(colrequired);

	}


}
