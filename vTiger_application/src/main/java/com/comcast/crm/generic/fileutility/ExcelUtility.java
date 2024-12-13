package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDatafromExcelfle(String sheetName,int rowNum,int cellNum) throws Throwable {
		
		FileInputStream fis= new FileInputStream(IPathConstant.excelpath);
		Workbook w = WorkbookFactory.create(fis);
		//String data = w.getSheet(sheetName).getRow(rowNum).getCell(cellNum).toString();
		Cell cell = w.getSheet(sheetName).getRow(rowNum).getCell(cellNum);
		
		DataFormatter data=new DataFormatter();
		String value = data.formatCellValue(cell);
		
		w.close();
		
		return value;
	}
	
	public int getRowcount(String sheetName) throws Throwable
	{
		FileInputStream fis= new FileInputStream(IPathConstant.excelpath);
		Workbook w = WorkbookFactory.create(fis);
		int rowNum = w.getSheet(sheetName).getLastRowNum();
		w.close();
		
		return rowNum;
	}
	
	public void setdataIntoExcel(String sheetName,int rowNum,int cellNum,String data) throws Throwable
	{
		FileInputStream fis= new FileInputStream(IPathConstant.excelpath);
		Workbook w = WorkbookFactory.create(fis);
		w.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		
		FileOutputStream fos=new FileOutputStream(IPathConstant.excelpath);
		w.write(fos);
		w.close();
		
	}

}
