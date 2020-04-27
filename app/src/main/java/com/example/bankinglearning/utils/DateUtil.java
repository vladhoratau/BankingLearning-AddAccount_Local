package com.example.bankinglearning.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String SIMPLE_DATE_FORMAT = "dd-MM-yyyy";

    public static Date getDate(String data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
        Date myDate = null;
        try {
            myDate = dateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDate;

    }
}
