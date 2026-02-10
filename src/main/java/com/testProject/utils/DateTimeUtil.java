package com.testProject.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

@Component
public class DateTimeUtil {


    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
    DateTimeFormatter dateFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
    DateTimeFormatter timeStampFormatter= DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a",Locale.ENGLISH);
    DateTimeFormatter timeStampFormatter2=DateTimeFormatter.ofPattern("dd/MMM/yyyy hh:mm a");
    DateTimeFormatter timeStampFormatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter timeStampFormatter4 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    DateTimeFormatter timeFormatter1 = DateTimeFormatter.ofPattern("HH:mm");
    DateTimeFormatter mmtDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");


    private static final DateTimeFormatter strDate=new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("dd-MMM-uuuu")
            .optionalStart().appendLiteral(' ').appendPattern("HH:mm[:ss]").optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY,0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR,0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE,0)
            .toFormatter(Locale.ENGLISH);


    public LocalDate mmtDepartureDateTimeFormat(String s)
    {
        return LocalDate.parse(s.trim(),strDate);
    }

    public LocalDateTime mmtDepartureDateTimeFormat2(String str)
    {
        return LocalDateTime.parse(str,mmtDateTimeFormatter);
    }

    public LocalDateTime differentFormat2(String s)
    {
        return LocalDateTime.parse(s.trim(),strDate);
    }

    public String dateFormatter(LocalDate date)
    {
        return date.format(dateFormatter);
    }

    public String timeFormatter(LocalTime time)
    {
        return time.format(timeFormatter);
    }

    public LocalTime stringTimeFormatter(String time)
    {
        return LocalTime.parse(time,timeFormatter1);
    }

    public String timeStampFormatter(LocalDateTime timestamp)
    {
        return timestamp.format(timeStampFormatter);
    }

    public LocalDate getLocalDateFromTimeStamp(String date)
    {
        LocalDateTime dateTime=LocalDateTime.parse(date,timeStampFormatter3);
        return dateTime.toLocalDate();
    }

    public LocalDate getLocalDate(String date)
    {
        return LocalDate.parse(date,dateFormatter1);
    }

    public LocalDateTime getTimeStamp4(String dt)
    {
        return LocalDateTime.parse(dt,timeStampFormatter4);
    }




}
