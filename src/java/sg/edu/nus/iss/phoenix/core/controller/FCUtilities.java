package sg.edu.nus.iss.phoenix.core.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FCUtilities {
	//Get userId from the servlet pathInfo    
    public static String stripPath(String pathInfo){
        int pos = pathInfo.indexOf("/");
        int len = pathInfo.length();
        String userId = pathInfo.substring(pos+1,len);
        System.out.println("Path: " + userId);
        return userId;
    }
    
    //get the date of the first day of a week.
    public static String getFirstDayOfWeek(String dateOfProg){
   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date =null;
     Calendar c = Calendar.getInstance();
    Date weekStart =null ;
        try {
            c.setFirstDayOfWeek(Calendar.MONDAY);
            date = formatter.parse(dateOfProg);
            c.setTime(date);
            int today = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DAY_OF_WEEK, -today+Calendar.MONDAY);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            
            System.out.println("print the formatted date after getting forst day of the week " +formatter.format(c.getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(FCUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
         return formatter.format(c.getTime());
    }
    
    // get the period in number of days between two dates. 
    public static int getPeriod(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int diffInDays = 0;
        try {
        Date sDate = sdf.parse(startDate);
        Date eDate = sdf.parse(endDate);
        diffInDays = (int)( (eDate.getTime() - sDate.getTime())/ (1000 * 60 * 60 * 24) );

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return diffInDays;
    }
    
    // add number of days to a date. 
    public static String addDaysToDate(String date, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
            c.add(Calendar.DATE, days);
            date = sdf.format(c.getTime());
        }catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date;
    }
    
    //fetches year from a date. 
    public static String fetchYearFromDate(String date) {
        String data[] = date.split("-");
        String year = data[0];
        return year;
    }
}
