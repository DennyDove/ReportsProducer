package ru.aig.Linde.services;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import ru.aig.Linde.utils.SumDTO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class AccountingReport {

    private XWPFDocument docx;
    private final String templateDocx = "src/main/resources/templates/Акт о платежах_v1.docx";

    public AccountingReport() {
    }

    public void inputValuesDocx(List<SumDTO> sumList) {
        docx = openDocx();
        int numberOfMonths = sumList.size();
        String totalSum = numericFormat(totalSum(sumList));
        String startPeriod = sumList.get(0).getStartPeriod();
        String endPeriod = sumList.get(numberOfMonths - 1).getEndPeriod();
        String year = sumList.get(0).getYear();
        String nds = "НДС 20%";

        XWPFParagraph p1 = docx.getParagraphArray(7);
        List<XWPFRun> runArray = p1.getRuns();
        String s1 = String.format(" В соответствии с п. 6 дополнительного соглашения №2 к договору поставки промышленных " +
                "газов от 18.12.2008 г. (договор «Клин 2») в период с %s %s г. по %s %s г.  суммы компенсации " +
                "за потребленные энергоресурсы в помесячной разбивке подлежащие выплате Стороной 2 в пользу Стороны 1 " +
                "составляют:", startPeriod, year, endPeriod, year);
        runArray.get(1).setText(s1, 0);

        p1 = docx.getParagraphArray(8);
        XWPFRun run;
        for(int i = 0; i < numberOfMonths; i++) {
            String sum = numericFormat(sumList.get(i).getMonthlySum());
            String current_month = sumList.get(i).getEndPeriod();
            run = p1.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(11);
            run.addBreak();
            run.addTab(); //
            run.setText(String.format("- за %s %s г. \u2012 %s руб;", current_month, year, sum), 0);
            p1.setAlignment(ParagraphAlignment.LEFT);
        }

        p1 = docx.getParagraphArray(10);
        runArray = p1.getRuns();
        String s2 = String.format("Общая сумма компенсации за потребленные энергоресурсы в период с %s %s г. по %s %s г. " +
                "составила %s руб. (В данную сумму не включен %s).", startPeriod, year, endPeriod, year, totalSum, nds);
        runArray.get(1).setText(s2, 0);
    }

    double totalSum (List<SumDTO> sumList) {
        double totalSum = 0;
        for (SumDTO sumDTO : sumList) {
            totalSum += sumDTO.getMonthlySum();
        }
        return totalSum;
    }

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

    private XWPFDocument openDocx() {
        try (FileInputStream is = new FileInputStream(templateDocx)) { // try-with-resources
            docx = new XWPFDocument(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docx;
    }

    public void save() {
        String file = "src/main/reports/out_report.docx";
        try {
            FileOutputStream output = new FileOutputStream(file);
            docx.write(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
