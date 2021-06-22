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
import org.infosystema.iselect.dto.BankAccountClient;
import org.infosystema.iselect.util.Util;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class ClientAccountsExcelParser extends BaseExcelParser {
	
	public static EntryValue<String, List<BankAccountClient>> parse(InputStream in, DocumentTypeEnum typeEnum, String format) throws ParseException {
		StringBuffer buffer = new StringBuffer();
		List<BankAccountClient> list = new ArrayList<>();

		//System.out.println("format = " + format);
		List<Field> fields = Util.getAllFields(BankAccountClient.class);

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

		//System.out.println("parced count payments = " + list.size());

		return new EntryValue<String, List<BankAccountClient>>(buffer.toString(), list);
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

	private static void parseRow(List<BankAccountClient> list, StringBuffer buffer, Row row, Map<String, Integer> headerMap, List<Field> fields) {
		//System.out.println("parseProduct");
		try {
			if(!isDigit(getStringCellValue(row.getCell(headerMap.get("contractorTin"))))) return;
			
			BankAccountClient client = new BankAccountClient();
			
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
