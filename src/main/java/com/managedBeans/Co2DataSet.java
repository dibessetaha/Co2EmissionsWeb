//package com.managedBeans;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;   
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import com.dao.ICo2Emissions;
//import com.entities.Co2Emission;
//import com.metier.Co2EmissionsImp;
//
//import jakarta.ejb.EJB;
//
//public class Co2DataSet {
//	
//	
//	ICo2Emissions metier = new Co2EmissionsImp(); 
//	
//	public void co2Datset() { 
//		 List<String> years = new ArrayList<>();
//	        try {
//	            FileInputStream file = new FileInputStream(new File("C:/Users/dibes/Downloads/co2-emissions-data.xlsx"));
//	            Workbook workbook = new XSSFWorkbook(file);
//	            Sheet sheet = workbook.getSheetAt(0);
//
//	            // Read years from the first row
//	            Row firstRow = sheet.getRow(0);
//	            for (int i = 1; i < firstRow.getLastCellNum(); i++) {
//	                Cell cell = firstRow.getCell(i);
//	                years.add(cell.toString());
//	            }
//
//	            // Read country data from subsequent rows
//	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//	                Row row = sheet.getRow(i);
//	                String countryName = row.getCell(0).toString();
//	                for (int j = 1; j < row.getLastCellNum(); j++) {
//	                 Cell cell = row.getCell(j); 
//	                 metier.addCo2Emisson(new Co2Emission(years.get(j - 1),countryName,cell.toString(), false)) ; 
//	                 
//	                }
//	            }
//
//	            workbook.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        } 
//	        
//	}
//
//}
