package org.infosystema.iselect.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class DateUtil {
	
	public static boolean isSameDay(Date date1, Date date2) {
	      if (date1 == null || date2 == null) {
	          throw new IllegalArgumentException("The dates must not be null");
	      }
	      Calendar cal1 = Calendar.getInstance();
	      cal1.setTime(date1);
	      Calendar cal2 = Calendar.getInstance();
	      cal2.setTime(date2);
	      return isSameDay(cal1, cal2);
	  }
	  
	  public static boolean isSameDay(Calendar cal1, Calendar cal2) {
	      if (cal1 == null || cal2 == null) {
	          throw new IllegalArgumentException("The dates must not be null");
	      }
	      return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
	              cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
	              cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	  }
	  
	  public static boolean isToday(Date date) {
	      return isSameDay(date, Calendar.getInstance().getTime());
	  }
	  
	  public static boolean isToday(Calendar cal) {
	      return isSameDay(cal, Calendar.getInstance());
	  }
	  
	  public static boolean isAfterDay(Date date1, Date date2) {
	      if (date1 == null || date2 == null) {
	          throw new IllegalArgumentException("The dates must not be null");
	      }
	      Calendar cal1 = Calendar.getInstance();
	      cal1.setTime(date1);
	      Calendar cal2 = Calendar.getInstance();
	      cal2.setTime(date2);
	      return isAfterDay(cal1, cal2);
	  }
	  
	  public static boolean isAfterDay(Calendar cal1, Calendar cal2) {
	      if (cal1 == null || cal2 == null) {
	          throw new IllegalArgumentException("The dates must not be null");
	      }
	      if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA)) return false;
	      if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA)) return true;
	      if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) return false;
	      if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) return true;
	      return cal1.get(Calendar.DAY_OF_YEAR) > cal2.get(Calendar.DAY_OF_YEAR);
	  }

}
