package ru.aig.Linde.utils;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SumDTO {

    private double monthlySum;
    private String year;
    private CalendarUtils calendarUtils;

    private String startPeriod = "";
    private String endPeriod = "";

    private final String January_startPeriod = "января";
    private final String January_endPeriod = "январь";
    private final String February_startPeriod = "февраля";
    private final String February_endPeriod = "февраль";
    private final String March_startPeriod = "марта";
    private final String March_endPeriod = "март";
    private final String April_startPeriod = "апреля";
    private final String April_endPeriod = "апрель";
    private final String May_startPeriod = "мая";
    private final String May_endPeriod = "май";
    private final String June_startPeriod = "июня";
    private final String June_endPeriod = "июнь";
    private final String July_startPeriod = "июля";
    private final String July_endPeriod = "июль";
    private final String August_startPeriod = "августа";
    private final String August_endPeriod = "август";
    private final String September_startPeriod = "сентября";
    private final String September_endPeriod = "сентябрь";
    private final String October_startPeriod = "октября";
    private final String October_endPeriod = "октябрь";
    private final String November_startPeriod = "ноября";
    private final String November_endPeriod = "ноябрь";
    private final String December_startPeriod = "декабря";
    private final String December_endPeriod = "декабрь";

    public SumDTO(double monthlySum, int month, String year) {
        this.monthlySum = monthlySum;
        this.year = year;

        if(month == 0) {
            this.startPeriod = January_startPeriod;
            this.endPeriod = January_endPeriod;
        }
        if(month == 1) {
            this.startPeriod = February_startPeriod;
            this.endPeriod = February_endPeriod;
        }
        if(month == 2) {
            this.startPeriod = March_startPeriod;
            this.endPeriod = March_endPeriod;
        }
        if(month == 3) {
            this.startPeriod = April_startPeriod;
            this.endPeriod = April_endPeriod;
        }
        if(month == 4) {
            this.startPeriod = May_startPeriod;
            this.endPeriod = May_endPeriod;
        }
        if(month == 5) {
            this.startPeriod = June_startPeriod;
            this.endPeriod = June_endPeriod;
        }
        if(month == 6) {
            this.startPeriod = July_startPeriod;
            this.endPeriod = July_endPeriod;
        }
        if(month == 7) {
            this.startPeriod = August_startPeriod;
            this.endPeriod = August_endPeriod;
        }
        if(month == 8) {
            this.startPeriod = September_startPeriod;
            this.endPeriod = September_endPeriod;
        }
        if(month == 9) {
            this.startPeriod = October_startPeriod;
            this.endPeriod = October_endPeriod;
        }
        if(month == 10) {
            this.startPeriod = November_startPeriod;
            this.endPeriod = November_endPeriod;
        }
        if(month == 11) {
            this.startPeriod = December_startPeriod;
            this.endPeriod = December_endPeriod;
        }
    }
}
