package ru.aig.Linde.services;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import ru.aig.Linde.utils.CalendarUtils;

@Service
public class MainService {

    @Autowired
    ExcelReport1 excelReport1;

    @Autowired
    UpdatePricesService updatePricesService;

    private WordDocument wordDocument;
    private ExcelReport2 excelReport2;
    private CalendarUtils calendarUtils;
    private double totalSum = 0;
    private double[] compensation = new double[12];

//  Главный метод паттерна "Facade"
    public void documentHandler (MultipartFile[] file) {
        calendarUtils = new CalendarUtils();
        int current_month;
        String current_year ="";
        for (int i = 0; i < file.length; i++) {
            //   Попробовал получить функционал методов JPA-репозитория через создание объектов,
            //   но так не работает! Вылетает ошибка NPE, т.к. PricesRepository = null
            //   excelReport = new CompensationReport();
            //   excelReport.inputCounterValues();
            //   excelReport.inputElecPrices();

            current_month = calendarUtils.getMonth(file[i]); // определяем текущий отчетный месяц (из названия файла)
            current_year = calendarUtils.getYear(file[i]); // определяем текущий год (из названия файла)

            wordDocument = new WordDocument(file[i]); // создается новый объект документа
            excelReport1.inputCounterValues();
            excelReport1.inputCalendarValues(file[i]);
            excelReport1.inputGasPrices(current_month);
            excelReport1.inputElecPrices(current_month);
            compensation[i] = excelReport1.save(current_month, current_year); // терминальный метод
            totalSum += compensation[i];

//          xlsx = excelReport1.save(current_month, current_year); // терминальный метод
        }
        // toDO
        excelReport2 = new ExcelReport2(compensation);
    }
}
