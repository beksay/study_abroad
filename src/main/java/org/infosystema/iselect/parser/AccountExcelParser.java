package org.infosystema.iselect.parser;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.infosystema.iselect.beans.DocumentTypeEnum;
import org.infosystema.iselect.beans.EntryValue;
import org.infosystema.iselect.dto.BankAccount;
import org.infosystema.iselect.util.Util;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class AccountExcelParser extends BaseExcelParser {
	
	public static EntryValue<String, List<BankAccount>> parse(InputStream in, DocumentTypeEnum typeEnum, String format) throws ParseException {
		StringBuffer buffer = new StringBuffer();
		List<BankAccount> list = new ArrayList<>();

		//System.out.println("format = " + format);
		List<Field> fields = Util.getAllFields(BankAccount.class);

		Iterator<Row> it = getRows(in);
		int i = 0;
		Map<String, Integer> headerMap = new HashMap<String, Integer>();

		while (it.hasNext()) {
			Row row = it.next();
			i++;
			if(i == HEADER_COLUMN) {
				parseHeader(row, headerMap, fields);
			} else {
				parseRow(list, buffer, row, headerMap, fields);
			}
				
		}

		return new EntryValue<String, List<BankAccount>>(buffer.toString(), list);
	}

	private static void parseHeader(Row row, Map<String, Integer> headerMap, List<Field> fields) {
		List<String> classFieldNames = fields.stream()
                .map(Field::getName)
                .collect(Collectors.toList());
		
		for (short i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			String value = getStringCellValue(row.getCell(i));
			if(value == null) continue;
			
			for (String string : classFieldNames) {
				if(value.contains(string)) headerMap.put(string, (int)i);
			}
		}
	}

	private static void parseRow(List<BankAccount> list, StringBuffer buffer, Row row, Map<String, Integer> headerMap, List<Field> fields) {
		try {
			if(!isDigit(getStringCellValue(row.getCell(headerMap.get("serialNumber"))))) return;
			
			BankAccount client = new BankAccount();
			
			for (Field field : fields) {
				String value = getStringCellValue(row.getCell(headerMap.get(field.getName())));
				
				field.setAccessible(true);
				field.set(client, value);
			}

			list.add(client);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

}
