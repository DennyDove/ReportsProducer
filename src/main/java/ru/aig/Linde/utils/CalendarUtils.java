package ru.aig.Linde.utils;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Setter
@Getter
@NoArgsConstructor
public class CalendarUtils {

//  Временное упрощение
//    private String year = "2024";

    private String startPeriod = "";
    private String endPeriod = "";

    private String January = "01.01.";
    private String February = "01.02.";
    private String March = "01.03.";
    private String April = "01.04.";
    private String May = "01.05.";
    private String June = "01.06.";
    private String July = "01.07.";
    private String August = "01.08.";
    private String September = "01.09.";
    private String October = "01.10.";
    private String November = "01.11.";
    private String December = "01.12.";

    private float hoursInMonth = 0;

    public CalendarUtils(String startPeriod, String endPeriod) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }



/*
    String getStartPeriod(int month){
        if(month == Project1.JANUARY) startPeriod = January+year;
        if(month == Project1.FEBRUARY) startPeriod = February+year;
        if(month == Project1.MARCH) startPeriod = March+year;
        if(month == Project1.APRIL) startPeriod = April+year;
        if(month == Project1.MAY) startPeriod = May+year;
        if(month == Project1.JUNE) startPeriod = June+year;
        if(month == Project1.JULY) startPeriod = July+year;
        if(month == Project1.AUGUST) startPeriod = August+year;
        if(month == Project1.SEPTEMBER) startPeriod = September+year;
        if(month == Project1.OCTOBER) startPeriod = October+year;
        if(month == Project1.NOVEMBER) startPeriod = November+year;
        if(month == Project1.DECEMBER) startPeriod = December+year;
        return startPeriod;
    }

    String getEndPeriod(int month){
        if(month == Project1.JANUARY) endPeriod = February+year;
        if(month == Project1.FEBRUARY) endPeriod = March+year;
        if(month == Project1.MARCH) endPeriod = April+year;
        if(month == Project1.APRIL) endPeriod = May+year;
        if(month == Project1.MAY) endPeriod = June+year;
        if(month == Project1.JUNE) endPeriod = July+year;
        if(month == Project1.JULY) endPeriod = August+year;
        if(month == Project1.AUGUST) endPeriod = September+year;
        if(month == Project1.SEPTEMBER) endPeriod = October+year;
        if(month == Project1.OCTOBER) endPeriod = November+year;
        if(month == Project1.NOVEMBER) endPeriod = December+year;
        if(month == Project1.DECEMBER) {
            int next_year = Integer.parseInt(year)+1;
 //           String next_year = String.valueOf(calc_next_year);
            endPeriod = January+next_year;
        }
        return endPeriod;
    }

 */

//  Определяем месяц из названия файла
public int getMonth(MultipartFile file) {
    String num;
    int month;
    num = file.getOriginalFilename().substring(0, 2);
    month = Integer.parseInt(num)-1; // число месяца уменьшаем на 1 --> для приведения к соответствию с порядковыми номерами массива
    return month;
}

//  Определяем год из названия файла
    public String getYear(MultipartFile file) {
        String year;
        year = file.getOriginalFilename().substring(3, 5);
        return year;
    }

//  Определяем число часов в месяце
    public double getHoursInMonth(MultipartFile file) {
        String num1;
        String num2;

        num1 = file.getOriginalFilename().substring(0,2);
        int month = Integer.parseInt(num1);
        num2 = file.getOriginalFilename().substring(3,5);
        int year = 2000 + Integer.parseInt(num2);

        Calendar startDate = new GregorianCalendar(year, month-1, 1);
        Calendar endDate = new GregorianCalendar(year, month, 1);
        hoursInMonth = endDate.getTimeInMillis() - startDate.getTimeInMillis();

        return hoursInMonth/1000/60/60;
    }

    //  Генерируем начальную дату в нужном цифровом формате
    public String getStartDate(MultipartFile file) {
        String day = "01";
        String month = file.getOriginalFilename().substring(0,2);
        String year = file.getOriginalFilename().substring(3,5);
        int yearInt = 2000 + Integer.parseInt(year);
        year = String.valueOf(yearInt);

        startPeriod = day + "." + month + "." + year;
        return startPeriod;
    }

    //  Генерируем конечную дату в нужном цифровом формате
    public String getEndDate(MultipartFile file) {
        String day = "01";
        String month = file.getOriginalFilename().substring(0,2);
        int monthInt = Integer.parseInt(month) + 1; // месяц увеличиваем на 1
        if(monthInt<10) month = "0" + monthInt;
        else if(monthInt >10) month = String.valueOf(monthInt);

        String year = file.getOriginalFilename().substring(3,5);
        int yearInt = 2000 + Integer.parseInt(year);
        year = String.valueOf(yearInt);

        endPeriod = day + "." + month + "." + year;
        return endPeriod;
    }
}
