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
    CalcSheet calcSheet;

    private ActDoc actDoc;
    private SumSheet sumSheet;
    private AccountingReport accountingReport;

//  Главный метод паттерна "Facade"
    public byte[] documentHandler (MultipartFile[] file) {
        List<SumDTO> sumList = new ArrayList<>();
        int current_month;
        String current_year ="";
        for (int i = 0; i < file.length; i++) {
            //   Попробовал получить функционал методов JPA-репозитория через создание обычных объектов (не бинов),
            //   но так не работает! Вылетает ошибка NPE, т.к. PricesRepository = null

            current_month = CalendarUtils.getMonth(file[i]); // определяем текущий отчетный месяц (из названия файла)
            current_year = CalendarUtils.getYear(file[i]); // определяем текущий год (из названия файла)

            actDoc = new ActDoc(file[i]); // открываем документ "Акт потребления энергоресурсов" и парсим из него нужные данные

//          1) Заполнение первого отчета: "Таблица рассчета компенсации электроэнергии"
            calcSheet.inputCounterValues();
            calcSheet.inputCalendarValues(file[i]);
            calcSheet.inputGasPrices(current_month);
            calcSheet.inputElecPrices(current_month);
            compensation[i] = calcSheet.save(current_month, current_year); // терминальный метод, все внесенные данные сохраняются в файл.
                                                                              // Вместе с этим сумма компенсации сохраняется в массив
//          Создание вспомогательного объекта для обмена данными
            SumDTO sumDTO = new SumDTO(compensation[i], current_month, current_year);
            sumList.add(sumDTO);

            //Более оптимальный вариант записи:
            //sumList.add(new SumDTO(compensation[i], current_month, current_year));
        }
//      Создание вспомогательной excel-таблицы
        sumSheet = new SumSheet(compensation);

//      2) Составление второго отчета: "Акт о суммах компенсации за электроэнергию"
        accountingReport = new AccountingReport();
        accountingReport.inputValuesDocx(sumList);
        byte[] report = accountingReport.save();
        sumList.clear();
    return report;
    }
}
