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

    public int getMonth(Date date){

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();

        /*Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);*/


        return month;
    }
}
