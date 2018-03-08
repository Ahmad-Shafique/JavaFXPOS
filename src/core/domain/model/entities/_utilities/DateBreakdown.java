package core.domain.model.entities._utilities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateBreakdown {

    private static DateBreakdown _instance;

    public static DateBreakdown getInstance(){
        if(_instance == null)_instance = new DateBreakdown();
        return _instance;
    }

    private DateBreakdown(){

    }

    public String getMonth(Date date){

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        String result ="";

        /*Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);*/

        if(month == 0) result = "January";
        else if(month == 1) result = "February";
        else if(month == 2) result = "March";
        else if(month == 3) result = "April";
        else if(month == 4) result = "May";
        else if(month == 5) result = "June";
        else if(month == 6) result = "July";
        else if(month == 7) result = "August";
        else if(month == 8) result = "September";
        else if(month == 9) result = "October";
        else if(month == 10) result = "November";
        else if(month == 11) result = "December";



        return result;
    }
}
