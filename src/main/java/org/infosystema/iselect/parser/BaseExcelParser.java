package org.infosystema.iselect.parser;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public abstract class BaseExcelParser {
	
	protected static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	protected static final DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");
	protected static final int HEADER_COLUMN = 1;
	
	protected static Iterator<Row> getRows(InputStream in) {
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(in);
		} catch (IOException | InvalidFormatException e) {
			e.printStackTrace();
		}

		Sheet sheet = wb.getSheetAt(0);
		Iterator<Row> it = sheet.iterator();
		return it;
	}
	
	protected static String getStringCellValue(Cell cell) {
		if (cell == null)
			return null;
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMaximumFractionDigits(0);
		numberFormat.setMinimumFractionDigits(0);
		numberFormat.setGroupingUsed(false);

		switch (cell.getCellTypeEnum()) {
		case STRING:
			//System.out.println("CELL type is string");
			return cell.getStringCellValue();
		case NUMERIC:
			//System.out.println("CELL type is numberic");
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				//System.out.println("cell.getColumnIndex() = " + cell.getColumnIndex());
				switch (cell.getColumnIndex()) {
				case 1:
					return TIME_FORMAT.format(cell.getDateCellValue());
				default:
					return DATE_FORMAT.format(cell.getDateCellValue());
				}
			}
			return numberFormat.format(cell.getNumericCellValue());
		case BOOLEAN:
			//System.out.println("CELL type is boolean");
			return cell.getBooleanCellValue() + "";
		default:
			return null;
		}
	}

	protected static boolean isDigit(String string) {
		try {
			Long.parseLong(string);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

}
