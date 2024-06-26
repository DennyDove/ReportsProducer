package ru.aig.Linde.services;

import lombok.Getter;
import org.apache.poi.hwpf.HWPFDocument;
import org.springframework.web.multipart.MultipartFile;
import ru.aig.Linde.utils.NumericUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Getter --> аннотация не применяется к статическим полям
public class ActDoc {
    private HWPFDocument doc;
    private List<Double> counterValues;
//  Counter H2 values (поля объявлены статическими для более оптимального доступа)
    @Getter private static Double H2_C_counterStart;
    @Getter private static Double H2_C_counterEnd;
    @Getter private static Double H2_D_counterStart;
    @Getter private static Double H2_D_counterEnd;

//  Counter electricity values
    @Getter private static Double elec_C_counterStart;
    @Getter private static Double elec_C_counterEnd;
    @Getter private static Double elec_D_counterStart;
    @Getter private static Double elec_D_counterEnd;

//  Energy resources price
    @Getter private static Double elec_price;
    @Getter private static Double gas_price;

    public ActDoc(MultipartFile file) {
        doc = openDoc(file);
        counterValues = parseDocument(doc);

//      Get H2-counter values for input
        H2_C_counterStart = counterValues.get(2);
        H2_C_counterEnd = counterValues.get(0);
        H2_D_counterStart = counterValues.get(3);
        H2_D_counterEnd = counterValues.get(1);
//      Get Electricity-counter values for input
        elec_C_counterStart = counterValues.get(6);
        elec_C_counterEnd = counterValues.get(4);
        elec_D_counterStart = counterValues.get(7);
        elec_D_counterEnd = counterValues.get(5);

    }

    private HWPFDocument openDoc(MultipartFile file) {
        try (InputStream is = file.getInputStream()) { // try-with-resources
            doc = new HWPFDocument(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    private List<Double> parseDocument(HWPFDocument doc) {
        counterValues = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\d{7}|\\d{8}|\\d{9})(\\.|,)(\\d*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(doc.getText());
        String value = ""; // инициализация переменной
        while (matcher.find()) {
            value = matcher.group();
            if (matcher.group(2).equals(".")) {
                value = matcher.group(1) + matcher.group(2) + matcher.group(3);
            } else if (matcher.group(2).equals(",")) {
                value = matcher.group(1) + "." + matcher.group(3);
                System.out.println("Parsed , ==> "+value);
            }
            counterValues.add(Double.parseDouble(value));
        }
        return counterValues;
    }
}
