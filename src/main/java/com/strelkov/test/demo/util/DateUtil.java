package com.strelkov.test.demo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    private Calendar date;
    int month;
    DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
    Map<String, List<String>> weeks = new TreeMap<>();

    public DateUtil(int month) {
        if (month <= 0 || month > 12)
            throw new IllegalArgumentException("Invalid month");
        date = new GregorianCalendar(2020, month - 1, 1);
        this.month = month;
    }

    public Map<String, List<String>> getWeeks() {
        while (date.get(Calendar.MONTH) != month) {
            StringBuilder daysInterval = new StringBuilder();
            List<String> daysOfWeek = new ArrayList<>();

            while (date.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && date.get(Calendar.MONTH) != month) {
                daysOfWeek.add(format.format(date.getTime()));
                date.add(Calendar.DAY_OF_WEEK, 1);
            }
            if (date.get(Calendar.MONTH) != month)
                daysOfWeek.add(format.format(date.getTime()));

            daysInterval.append(daysOfWeek.get(0)).append("-").append(daysOfWeek.get(daysOfWeek.size() - 1));
            weeks.put(daysInterval.toString(), daysOfWeek);
            date.add(Calendar.DAY_OF_WEEK, 1);
        }
        return weeks;
    }

}
