package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	// code to read/ write/ update excel file
	static Logger log = Logger.getLogger(ExcelUtil.class);

	public void getData(String file, String sheet) {

		log.info("Getting Data for the excel file: " + file + ", and for sheet: " + sheet);
		try {
			XSSFWorkbook workbook;
			workbook = new XSSFWorkbook(System.getProperty("user.dir") + "//XLSFiles//" + file + ".xlsx");
			XSSFSheet xssfSheet = workbook.getSheet(sheet);

			XSSFRow row;
			XSSFCell cell;
			Iterator rowIterator = xssfSheet.rowIterator();
			row = (XSSFRow) rowIterator.next();
			int count = 0;

			while (rowIterator.hasNext()) {
				row = (XSSFRow) rowIterator.next();

				// Iterating all the cells of the current row
				Iterator cells = row.cellIterator();

				while (cells.hasNext()) {
					cell = (XSSFCell) cells.next();

					if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
						System.out.print(cell.getStringCellValue() + " ");
					} else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
						System.out.print(cell.getNumericCellValue() + " ");
					} else if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
						System.out.print(cell.getBooleanCellValue() + " ");

					} else { // //Here if require, we can also add below methods
								// to
								// read the cell content
								// XSSFCell.CELL_TYPE_BLANK
								// XSSFCell.CELL_TYPE_FORMULA
								// XSSFCell.CELL_TYPE_ERROR
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * try {
		 * 
		 * while(count!=row.getPhysicalNumberOfCells() && rowIterator.hasNext())
		 * { int rowCount=0; List<String> columnValues= new
		 * LinkedList<String>(); Iterator cellIterator= row.cellIterator();
		 * while(cellIterator.hasNext()&&
		 * rowCount!=xssfSheet.getPhysicalNumberOfRows()) { cell = (XSSFCell)
		 * cellIterator.next();
		 * if(xssfSheet.getRow(rowCount).getCell(count).getCellType()!=cell.
		 * CELL_TYPE_BLANK) {
		 * System.out.print(xssfSheet.getRow(rowCount).getCell(count).
		 * getStringCellValue()); } else { //log.error("Cell doesn't have value"
		 * ); break; } rowCount++; } System.out.println(); count++;
		 */

		// List<>
		/*
		 * List<LinkedList<String>> header= new
		 * LinkedList<LinkedList<String>>();
		 * 
		 * LinkedList<String> values= new LinkedList<>(); values.add("pika");
		 * values.add("pika1"); values.add("pika2"); values.add("pika3");
		 * 
		 * header.add(values);
		 * 
		 * System.out.println();
		 * 
		 * System.out.println(xssfSheet.getRow(0).getPhysicalNumberOfCells());
		 */

	}

	public Map<Integer, List<Object>> getDataObject(String fileName, String sheet) {

		Map<Integer, List<Object>> dataValues = new HashMap<>();
		log.info("Getting Data for the excel file: " + fileName + ", and for sheet: " + sheet);
		try {
			XSSFWorkbook workbook;
			File file = new File(System.getProperty("user.dir")+"//XLSFiles//"+fileName+".xlsx");
			int count=0;
			log.info("File is available to read: "+file.canRead());
			while(file.canRead()!=true && count<5) {
				log.info("File is available to read: "+file.canRead()+", file exists: "+file.exists()+", so waiting for 250ms 5 times.");
				Thread.sleep(3000);
				count++;
			}
			FileInputStream fileInputStream= new FileInputStream(file);
			//workbook = new XSSFWorkbook(System.getProperty("user.dir") + "\\XLSFiles\\" + file + ".xlsx");
			workbook= 	new XSSFWorkbook(fileInputStream);
			XSSFSheet xssfSheet = workbook.getSheet(sheet);
			XSSFRow row;
			XSSFCell cell;
			Iterator<Row> rowIterator = xssfSheet.rowIterator();
			while (rowIterator.hasNext()) {
				row = (XSSFRow) rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				List<Object> values = new LinkedList<>();
				while (cellIterator.hasNext()) {
					cell = (XSSFCell) cellIterator.next();
					// System.out.println("couln inder"+cell.getColumnIndex());
					// System.out.println(cell.getStringCellValue());
					// cell.getCellType()
					values.add(cell.getStringCellValue());
				}
				// System.out.println("Row number: "+row.getRowNum());
				dataValues.put(row.getRowNum(), values);
			}
			workbook.close();
		} catch (Exception e) {
			log.error("Excel Util | Error has been occured while reading data from xlsx file: " + fileName);
			e.printStackTrace();
		}
		return dataValues;
	}

	public static void writeInExcel(String file, String sheet, int[] cells, String[] values) {

		// Map<Integer, List<Object>> dataValues= new HashMap<>();
		log.info("Writing Data in excel file: " + file + ", and for sheet: " + sheet);
		try {
			XSSFWorkbook workbook;
			FileInputStream fileInputStream = new FileInputStream(
					System.getProperty("user.dir") + "//XLSFiles//" + file + ".xlsx");
			workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet xssfSheet = workbook.getSheet(sheet);
			XSSFRow row = xssfSheet.createRow(1);
			XSSFCell cell;
			for (int i : cells) {
				cell = row.createCell(i);
				if (values[i] != null) {
					cell.setCellValue(values[i]);
					if (values[i].matches("^\\d+$")) {
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue(Integer.parseInt(values[i]));
					}
					// log.info("setting the value "+values[i]);
				}
			}
			log.info("Closing the input stream");
			fileInputStream.close();
			FileOutputStream fileOutputStream = new FileOutputStream(
					new File(System.getProperty("user.dir") + "//XLSFiles//" + file + ".xlsx"));
			workbook.write(fileOutputStream);
			log.info("Wrote the data in: " + file + " in sheet: " + sheet);
			workbook.close();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return dataValues;
	}

	public static void writeInExcelCell(String file, String sheet, int rowNumber, int cellNumber, String cellValue) {

		// Map<Integer, List<Object>> dataValues= new HashMap<>();
		log.info("Writing Data in excel file: " + file + ", and for sheet: " + sheet);
		try {
			XSSFWorkbook workbook;
			FileInputStream fileInputStream = new FileInputStream(
					System.getProperty("user.dir") + "//XLSFiles//" + file + ".xlsx");
			workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet xssfSheet = workbook.getSheet(sheet);
			XSSFRow row = xssfSheet.getRow(rowNumber);
			if (row == null) {
				log.info("Specified row: " + rowNumber + " is not found in the xlsx sheet. Creating it !!!");
				row = xssfSheet.createRow(rowNumber);
			}
			XSSFCell cell;
			cell = row.createCell(cellNumber);
			cell.setCellValue(cellValue);
			if (cellValue.matches("^\\d+$")) {
				log.info("Provided value contains the numreic part: " + cellValue
						+ " ,hence setting the cell type as numeric and writing the value: " + cellValue);
				cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
				cell.setCellValue(Integer.parseInt(cellValue));
			}
			log.info("Writing the value: " + cellValue + ", in cell number: " + cellNumber);
			log.info("Closing the input stream");
			fileInputStream.close();
			FileOutputStream fileOutputStream = new FileOutputStream(
					new File(System.getProperty("user.dir") + "//XLSFiles//" + file + ".xlsx"));
			workbook.write(fileOutputStream);
			log.info("Wrote the data in: " + file + " in sheet: " + sheet);
			workbook.close();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return dataValues;
	}

	/*public static void main(String[] args) {
		// int [] cells={0,1};
		/// String [] string= {"pika","chika"};
		// ExcelUtil.writeInExcel("pika", "Sheet1",cells,string);
		ExcelUtil.writeInExcelCell("step4NACHUpload", "active", 1, 16, "#17010007008999");
	}*/
	/*
	 * public static void main(String[] args) { ExcelUtil util= new ExcelUtil();
	 * Map<Integer,List<Object>> values= util.getDataObject("username", "user");
	 * for(java.util.Map.Entry<Integer, List<Object>> e: values.entrySet()) {
	 * System.out.println("values of row: "+e.getKey()); for(Object rowValue:
	 * e.getValue()) { System.out.print(rowValue); System.out.print("/t"); }
	 * System.out.println(); } }
	 */
}
