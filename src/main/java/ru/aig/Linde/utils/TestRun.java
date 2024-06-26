package ru.aig.Linde.utils;

import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.aig.Linde.services.AccountingReport;
import ru.aig.Linde.services.MainService;

import java.io.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TestRun {

    static XSSFWorkbook xlsx;

    public static void main(String[] args) {
/*
        FileUtils fileUtils = new FileUtils();
        fileUtils.docHandler();


        String num ="002";
        int month;
        month = Integer.parseInt(num);
        System.out.println(month);
 */

 /*
        String num1 = "02";
        String num2 = "24";

        int month = Integer.parseInt(num1);
        int year = 2000 + Integer.parseInt(num2);

        Calendar startDate = new GregorianCalendar(year, month-1, 1);  // month-1 ��� ��� � ������ Calendar ������ = 0,
        Calendar endDate = new GregorianCalendar(year, month, 1);

        float hoursInMonth = endDate.getTimeInMillis() - startDate.getTimeInMillis();

        System.out.println(hoursInMonth/1000/60/60);

*/
/*
        String day = "01.";
        String month;
        String year;

        String file = "07_23act_EE-AGC-Klin2.doc";

        month = file.substring(0,2);
        year = file.substring(3,5);
        int yearInt = 2000 + Integer.parseInt(year);

        year = String.valueOf(yearInt);

        String startPeriod = day + month + "."+year;

        System.out.println(startPeriod);
*/
/*
        double totalSum = 0;
        double[] compensation = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        try {
            FileInputStream is = new FileInputStream("src/main/resources/templates/total_sums.xlsx");
            xlsx = new XSSFWorkbook(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i =0; i < 12; i++) {
            xlsx.getSheetAt(0).getRow(5+i).getCell(1).setCellValue(compensation[i]);
            totalSum += compensation[i];
        }
        XSSFFormulaEvaluator.evaluateAllFormulaCells(xlsx);

        try {
            FileOutputStream output = new FileOutputStream("D:/Works/IT/Compensation.xlsx");
            xlsx.write(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
/*
        DataUtils dataUtils = new DataUtils();
        dataUtils.numericFormat(543418.77);
        dataUtils.numericFormat(5434818.77);
*/
 /*
        SumDTO sumDTO;
        List<SumDTO> sumList = new ArrayList<>();

        for(int i=0; i<5; i++) {
            sumDTO = new SumDTO(222234.54+i, 3+i, "2024");
            sumList.add(sumDTO);
        }

        AccountingReport accountingReport = new AccountingReport();
        accountingReport.inputValuesDocx(sumList);
        accountingReport.saveDocxFile();
*/

        /*
        String path = "src/main/resources/templates/compcalc.xlsx";
        File file = new File(path);
        String templateXLSX = file.getAbsolutePath();
        System.out.println(templateXLSX);
        */

    /*
        String path = "src/main/resources/templates";
        File file = new File(path);
        String templateXLSX = file.getAbsolutePath() + "/compcalc.xlsx";

     //   final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());


        try {
            FileInputStream is = new FileInputStream(templateXLSX);
            xlsx = new XSSFWorkbook(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream output = new FileOutputStream("monthly_power_compensation_Klin.xlsx")) { // try-with-resources
            xlsx.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        /*
        //Разобрался как из JAR файла делать выгрузку
        //String template = "D:/Works/IT/ReportsProducer/ReportsProducer_v2.jar";
        String template = "ReportsProducer_v2.jar";

        //String res = tr.getClass().getName();
        File file = new File(template);
        String absolutePath = file.getAbsolutePath();

        System.out.println(absolutePath);

        try {
            JarFile jar = new JarFile(template);
            final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
            while(entries.hasMoreElements()) {
                final String name = entries.nextElement().getName();
                if (name.contains("compcalc.xlsx")) { //filter according to name
                    JarEntry jarEntry = new JarEntry(name);
                    try (InputStream is = jar.getInputStream(jarEntry)) {
                        xlsx = new XSSFWorkbook(is);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Записываем извлеченный JAR-файл на диск
        try (FileOutputStream output = new FileOutputStream("monthly_power_compensation_Klin.xlsx")) { // try-with-resources
            xlsx.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    */

    }
}



