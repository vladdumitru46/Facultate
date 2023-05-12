package com.example;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        String stringTime = "10:30";
        DateFormat format = new SimpleDateFormat("HH:mm");
        try {
            Date date = format.parse(stringTime);
            Time time = new Time(date.getTime());
            System.out.println(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}