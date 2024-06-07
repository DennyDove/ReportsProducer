package ru.aig.Linde.services;

import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelReport2 {

    private XSSFWorkbook xlsx;
    private final String consReport = "src/main/resources/templates/total_sums.xlsx";

    public ExcelReport2(double[] compensation) {
        xlsx = open(consReport);
        for(int i =0; i < 12; i++) {
            xlsx.getSheetAt(0).getRow(5+i).getCell(1).setCellValue(compensation[i]);
        }
        XSSFFormulaEvaluator.evaluateAllFormulaCells(xlsx);
        save();
    }

    public XSSFWorkbook open(String xlsxDoc)  {
        try (FileInputStream is = new FileInputStream(xlsxDoc)) { // try-with-resources
            xlsx = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xlsx;
    }

//      Выгрузка отчетного файла на жесткий диск на период отладки программы
    private void save() {
        try {
            FileOutputStream output = new FileOutputStream("D:/Works/IT/Compensation.xlsx");
            xlsx.write(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

