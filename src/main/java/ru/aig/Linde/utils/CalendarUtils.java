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

    private static String startDate = "";
    private static String endDate = "";

    private static float hoursInMonth = 0;

//  Определяем месяц из названия файла
public static int getMonth(MultipartFile file) {
    String num;
    int month;
    num = file.getOriginalFilename().substring(0, 2);
    month = Integer.parseInt(num)-1; // число месяца уменьшаем на 1 --> для приведения к соответствию с порядковыми номерами массива
    return month;
}

//  Определяем год из названия файла
    public static String getYear(MultipartFile file) {
        String year;
        year = "20"+file.getOriginalFilename().substring(3, 5);
        return year;
    }

//  Определяем число часов в месяце
    public static double getHoursInMonth(MultipartFile file) {
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
    public static String getStartDate(MultipartFile file) {
        String day = "01";
        String month = file.getOriginalFilename().substring(0,2);
        String year = "20" + file.getOriginalFilename().substring(3,5);
//        int yearInt = 2000 + Integer.parseInt(year);
//        year = String.valueOf(yearInt);

        startDate = day + "." + month + "." + year;
        return startDate;
    }

    //  Генерируем конечную дату в нужном цифровом формате
    public static String getEndDate(MultipartFile file) {
        String day = "01";
        String month = file.getOriginalFilename().substring(0,2);
        int monthInt = Integer.parseInt(month) + 1; // месяц увеличиваем на 1
        if(monthInt<10) month = "0" + monthInt;
        else if(monthInt >10) month = String.valueOf(monthInt);

        String year = "20" + file.getOriginalFilename().substring(3,5);
//        int yearInt = 2000 + Integer.parseInt(year);
//        year = String.valueOf(yearInt);

        endDate = day + "." + month + "." + year;
        return endDate;
    }
}
