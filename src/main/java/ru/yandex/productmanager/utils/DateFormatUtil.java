package ru.yandex.productmanager.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    public static Date format(String date) {
        try {
            Date formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(date);
            return formatDate;
        }catch (Exception parseException){
            throw new IllegalArgumentException("date parse ex");
        }

    }
}
