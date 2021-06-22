package org.infosystema.iselect.parser;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.infosystema.iselect.beans.DocumentTypeEnum;
import org.infosystema.iselect.beans.EntryValue;
import org.infosystema.iselect.dto.Product;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class ProductExcelParser extends BaseExcelParser {

	public static EntryValue<String, List<Product>> parse(InputStream in, DocumentTypeEnum typeEnum, String format) throws ParseException {
		StringBuffer buffer = new StringBuffer();
		List<Product> products = new ArrayList<>();

		//System.out.println("format = " + format);

		Iterator<Row> it = getRows(in);

		while (it.hasNext()) {
			Row row = it.next();
			if (DocumentTypeEnum.PRODUCT.equals(typeEnum)) {
				parseProduct(products, buffer, row);
			} else if(DocumentTypeEnum.SERVICE.equals(typeEnum)) {
				parseService(products, buffer, row);
			}
		}

		//System.out.println("parced count payments = " + products.size());

		return new EntryValue<String, List<Product>>(buffer.toString(), products);
	}
	
	private static void parseService(List<Product> products, StringBuffer buffer, Row row) {
		//System.out.println("parseService");
		try {
			String s = getStringCellValue(row.getCell(2));
			if(s != null) s = s.replaceAll("\\.", "");
			if(!isDigit(s)) return;
			
			String productName = getStringCellValue(row.getCell(0));
			String unitName = getStringCellValue(row.getCell(1));
			String code = getStringCellValue(row.getCell(2));
			
			if(productName == null || productName.isEmpty()) return;
			
			Product product = new Product(productName, unitName, code, false);
			products.add(product);

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	private static void parseProduct(List<Product> products, StringBuffer buffer, Row row) {
		//System.out.println("parseProduct");
		try {
			if(!isDigit(getStringCellValue(row.getCell(2)))) return;
			
			String productName = getStringCellValue(row.getCell(0));
			String unitName = getStringCellValue(row.getCell(1));
			String code = getStringCellValue(row.getCell(2));
			
			if(productName == null || productName.isEmpty()) return;
			
			Product product = new Product(productName, unitName, code, true);
			products.add(product);

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

}
