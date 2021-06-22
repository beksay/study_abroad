package org.infosystema.iselect.conversion;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("decimalNumberFiveConverter")
public class DecimalNumberFiveConverter implements Converter<Object> {

    public Object getAsObject( FacesContext fc, UIComponent uic, String value ) {
        if( value != null ){
        	try{
        		return new BigDecimal( value.replaceAll("[[\\D]*&&[^\\,\\.\\-]]","").replace(',','.'));
        	}catch(Exception e){        		
        		return null;
        	}
        }
        return null;
    }

    public String getAsString( FacesContext fc, UIComponent uic, Object object ) {
    	if(!(object instanceof Number)) return object + "";
    	
        NumberFormat nf = NumberFormat.getInstance(new Locale("ru"));
        nf.setMaximumFractionDigits(5);
        nf.setMinimumFractionDigits(5);
        return nf.format(object);
    }
}
