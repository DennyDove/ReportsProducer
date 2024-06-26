package ru.aig.Linde.services;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ExcelDocument {
    protected XSSFWorkbook xlsx;
    protected double[] compensation = new double[12];

    public XSSFWorkbook open(String xlsxDoc)  {
        try (FileInputStream is = new FileInputStream(xlsxDoc)) { // try-with-resources
            xlsx = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xlsx;
    }

    //Метод для доступа к шаблонному файлу из jar-архива - работает не так как задумывалось
    //=======================================================================================
    public  XSSFWorkbook openJAR() {
        String template = "ReportsProducer_v3.jar";

        //String res = getClass().getName();
        File file = new File(template);
        String absolutePath = file.getAbsolutePath();

        try {
            JarFile jar = new JarFile(absolutePath);
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
            jar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return xlsx;
    }
    //=======================================================================================
}

