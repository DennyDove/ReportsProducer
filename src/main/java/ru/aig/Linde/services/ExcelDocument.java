package ru.aig.Linde.services;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.aig.Linde.utils.CalendarUtils;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDocument {
    protected XSSFWorkbook xlsx;
    protected CalendarUtils calendarUtils;
    protected double[] compensation = new double[12];

    public XSSFWorkbook open(String xlsxDoc)  {
        try (FileInputStream is = new FileInputStream(xlsxDoc)) { // try-with-resources
            xlsx = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xlsx;
    }
}

