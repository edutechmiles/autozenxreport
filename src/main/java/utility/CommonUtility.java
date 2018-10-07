package utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CommonUtility {
	public static String dateTimeFormat = "dd-MMM-yyyy hh:mm:ss";

	public static String now(String userDateFormat){
		Calendar cal = Calendar.getInstance();
		String dateTime = "";
		SimpleDateFormat sdf ;
		try {
			 sdf = new SimpleDateFormat(userDateFormat);
		}catch(Exception e) {
			 sdf = new SimpleDateFormat(dateTimeFormat);
		}
		dateTime = sdf.format(cal.getTime());

		return dateTime;
	}
}
