package ru.aig.Linde.utils;

public class NumericUtils {

//  Метод приводит числовое значение к нужному формату в документе Word
    public String numericFormat(double number) {

        String inputNumber = String.format("%.2f", number);
        String resultNumber = inputNumber.replace(".", ",");

        String firstPartOfNumber = resultNumber.substring(0, resultNumber.indexOf(","));
        if(firstPartOfNumber.length() == 6) {
            StringBuilder sb = new StringBuilder(resultNumber);
            resultNumber = sb.insert(3, " ").toString(); // вставляем разделяющий пробел
        }

        if(firstPartOfNumber.length() == 7) {
            StringBuilder sb = new StringBuilder(resultNumber);
            resultNumber = sb.insert(1, " ").toString(); // вставляем разделяющий пробел
            resultNumber = sb.insert(5, " ").toString(); // вставляем разделяющий пробел
        }
        return resultNumber;
    }
}
