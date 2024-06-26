package ru.aig.Linde.services;

import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;

import java.io.FileOutputStream;
import java.io.IOException;

public class SumSheet extends ExcelDocument {

    //Реализация загрузки шаблонов файлов прямо из jar - не работает
    //final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());

    private String sumsReport = "c:/templates/total_sums.xlsx";

    public SumSheet(double[] compensation) {
        xlsx = open(sumsReport);
        for(int i =0; i < 12; i++) {
            xlsx.getSheetAt(0).getRow(5+i).getCell(1).setCellValue(compensation[i]);
            compensation[i] = 0;
        }
        XSSFFormulaEvaluator.evaluateAllFormulaCells(xlsx);
        save();
    }

//  Выгрузка отчетного файла на жесткий диск на период отладки программы
    private void save() {
        try {
            FileOutputStream output = new FileOutputStream("c:/reports/Compensation.xlsx");
            xlsx.write(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

