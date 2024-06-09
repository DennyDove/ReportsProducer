package ru.aig.Linde.services;

import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.aig.Linde.utils.CalendarUtils;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ExcelReport1 extends ExcelDocument {

    @Autowired
    ElecPricesService elecPricesService;

    @Autowired
    GasPricesService gasPricesService;

    private CalendarUtils calendarUtils;
    private final double coefficient = 1.07322;
    private final String templateXLSX = "src/main/resources/templates/compcalc.xlsx";

//  Выгрузка отчетного файла на жесткий диск на период отладки программы
    public double save(int month, String year) {
        double compensationSum = xlsx.getSheetAt(0).getRow(81).getCell(2).getNumericCellValue();
//                                                                           month+1 т.к. здесь нам не нужно ориентироваться на массив, где порядковые номера начинаются с нуля
        try (FileOutputStream output = new FileOutputStream("src/main/reports/"+(month+1)+"_monthly_power_compensation_Klin 20"+year+".xlsx")) { // try-with-resources
            xlsx.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compensationSum;
    }

    public void inputCounterValues() {
        xlsx = open(templateXLSX);
//      Input values counter in excel
        xlsx.getSheetAt(0).getRow(24).getCell(1).setCellValue(ActDoc.getH2_C_counterStart());
        xlsx.getSheetAt(0).getRow(22).getCell(1).setCellValue(ActDoc.getH2_C_counterEnd());
        xlsx.getSheetAt(0).getRow(24).getCell(4).setCellValue(ActDoc.getH2_D_counterStart());
        xlsx.getSheetAt(0).getRow(22).getCell(4).setCellValue(ActDoc.getH2_D_counterEnd());
        xlsx.getSheetAt(0).getRow(39).getCell(1).setCellValue(ActDoc.getElec_C_counterStart());
        xlsx.getSheetAt(0).getRow(37).getCell(1).setCellValue(ActDoc.getElec_C_counterEnd());
        xlsx.getSheetAt(0).getRow(39).getCell(4).setCellValue(ActDoc.getElec_D_counterStart());
        xlsx.getSheetAt(0).getRow(37).getCell(4).setCellValue(ActDoc.getElec_D_counterEnd());
    }

    public void inputGasPrices(int month) {
        double gasPrice = gasPricesService.getPriceByMonth(month);
        xlsx.getSheetAt(0).getRow(58).getCell(3).setCellValue(gasPrice/1000);
        xlsx.getSheetAt(0).getRow(58).getCell(1).setCellValue(gasPrice*coefficient/1000); // умножаем на коэффициент 1.07322 для приведения к стандартным условиям
    }

    public void inputElecPrices(int month) {
        double elecPrice = elecPricesService.getPriceByMonth(month);
        xlsx.getSheetAt(0).getRow(54).getCell(1).setCellValue(elecPrice);
        XSSFFormulaEvaluator.evaluateAllFormulaCells(xlsx); // метод заставляет пересчитаться формулы при следующем открытии файла (возможно уже данный метод не требуется)
    }

    public void inputCalendarValues(MultipartFile file) {
        calendarUtils = new CalendarUtils();
        double hoursInMonth = calendarUtils.getHoursInMonth(file);

//      Устанавливаем начальную и конечную дату
        xlsx.getSheetAt(0).getRow(4).getCell(1).setCellValue(calendarUtils.getStartDate(file));
        xlsx.getSheetAt(0).getRow(5).getCell(1).setCellValue(calendarUtils.getEndDate(file));

//      Вносим число часов в месяце
        xlsx.getSheetAt(0).getRow(18).getCell(1).setCellValue(hoursInMonth);
        xlsx.getSheetAt(0).getRow(18).getCell(4).setCellValue(hoursInMonth);

//      метод заставляет пересчитаться формулы при следующем открытии файла (возможно уже данный метод не требуется)
        XSSFFormulaEvaluator.evaluateAllFormulaCells(xlsx);
    }
}




