package ru.aig.Linde.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import ru.aig.Linde.utils.CalendarUtils;
import ru.aig.Linde.utils.SumDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService extends ExcelDocument {

    @Autowired
    ExcelReport1 excelReport1;

    @Autowired
    UpdatePricesService updatePricesService;

    private ActDoc actDoc;
    private ExcelReport2 excelReport2;
    private AccountingReport accountingReport;
    private SumDTO sumDTO;
    public List<SumDTO> sumList = new ArrayList<>();

//  Главный метод паттерна "Facade"
    public void documentHandler (MultipartFile[] file) {
        calendarUtils = new CalendarUtils();
        int current_month;
        String current_year ="";
        for (int i = 0; i < file.length; i++) {
            //   Попробовал получить функционал методов JPA-репозитория через создание обычных объектов (не бинов),
            //   но так не работает! Вылетает ошибка NPE, т.к. PricesRepository = null

            current_month = calendarUtils.getMonth(file[i]); // определяем текущий отчетный месяц (из названия файла)
            current_year = calendarUtils.getYear(file[i]); // определяем текущий год (из названия файла)

            actDoc = new ActDoc(file[i]); // открываем документ "Акт потребления энергоресурсов" и парсим из него нужные данные

//          1) Заполнение первого отчета: "Таблица рассчета компенсации электроэнергии"
            excelReport1.inputCounterValues();
            excelReport1.inputCalendarValues(file[i]);
            excelReport1.inputGasPrices(current_month);
            excelReport1.inputElecPrices(current_month);
            compensation[i] = excelReport1.save(current_month, current_year); // терминальный метод, все внесенные данные сохраняются в файл.
                                                                              // Вместе с этим сумма компенсации сохраняется в массив
//          Создание вспомогательного объекта для обмена данными
            sumDTO = new SumDTO(compensation[i], current_month, current_year);
            sumList.add(sumDTO);
        }
//      Создание всаомогательной excel-таблицы
        excelReport2 = new ExcelReport2(compensation);

//      2) Составление второго отчета: "Акт о суммах компенсации за электроэнергию"
        accountingReport = new AccountingReport();
        accountingReport.inputValuesDocx(sumList);
        accountingReport.save();
        sumList.clear();
    }
}
