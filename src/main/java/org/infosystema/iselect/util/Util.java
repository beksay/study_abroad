package org.infosystema.iselect.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
	
	public static List<Field> getAllFields(Class<?> clazz) {
        if (clazz == null) {
            return Collections.emptyList();
        }

        List<Field> result = new ArrayList<>(getAllFields(clazz.getSuperclass()));
        List<Field> filteredFields = Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toList());
        result.addAll(filteredFields);
        return result;
    }
	
	private static BigDecimal round(BigDecimal amount, int i) {
        MathContext m = new MathContext(amount.toBigInteger().toString().length() + i, RoundingMode.HALF_DOWN);
  
        return amount.round(m); 
	}

	public static String dateToString(Date dateToConvert) {
        return dateToConvert.toString();
    }
}
