package org.infosystema.iselect.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class DateAdapter extends XmlAdapter<String, Date> {
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String marshal(Date v) throws Exception {
		if(v == null) return null;
		
		synchronized (dateFormat) {
			return dateFormat.format(v);
	    }
	}

	@Override
	public Date unmarshal(String v) throws Exception {
		if(v == null) return null;
		
		synchronized (dateFormat) {
            return dateFormat.parse(v);
        }
	}

}
