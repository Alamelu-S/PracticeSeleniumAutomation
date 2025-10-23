package com.cura.utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class ExcelReader {
	public static Object[][] getData(String filePath, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows - 1][cols];

		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
			}
		}

		workbook.close();
		fis.close();

		return data;

	}

}
