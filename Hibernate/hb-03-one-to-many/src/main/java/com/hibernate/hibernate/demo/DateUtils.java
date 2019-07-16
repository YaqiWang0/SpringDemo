package com.hibernate.hibernate.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");

    public static Date parseDate(String dateStr) throws ParseException{
        Date date=format.parse(dateStr);
        return date;
    }

    public static String formatDate(Date date){
        String result=null;
        if(date!=null){
            result=format.format(date);
        }
        return result;
    }
}
