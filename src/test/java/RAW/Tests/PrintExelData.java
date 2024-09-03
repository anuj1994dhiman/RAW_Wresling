package RAW.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PrintExelData {

	public static void main(String[] args) throws IOException {
		File exelData = new File("E:\\Realme 7\\cred.xlsx");
		FileInputStream exlFile = new FileInputStream(exelData);
		XSSFWorkbook wb = new XSSFWorkbook(exlFile);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getPhysicalNumberOfRows();
		XSSFRow r = sheet.getRow(0);
		int cellNum = r.getPhysicalNumberOfCells();
		Object[][] data = new Object[rowNum-1][cellNum];
		DataFormatter formater = new DataFormatter();
		for(int i=0; i<rowNum-1;i++) {
			XSSFRow row = sheet.getRow(i+1);
			for(int j=0;j<cellNum;j++) {
				XSSFCell cell= row.getCell(j);
				System.out.println(formater.formatCellValue(cell));
			}
		}
	}

	}
