package ru.aig.Linde.services;

//import jakarta.persistence.EntityManager;
//import jakarta.transaction.Transactional;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.aig.Linde.entities.ElecPrices;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class UpdatePricesService {

    @Autowired
    private GasPricesService gasPricesService;

    @Autowired
    private ElecPricesService elecPricesService;

    private List<XSSFWorkbook> xlsx = new ArrayList<>();

    public void persistPricesDB(MultipartFile[] file) {
        for (int i = 0; i < file.length; i++) {
            xlsx = open(file);
            if (file[i].getOriginalFilename().contains("gas")) {
                gasPricesService.setGasPrices(xlsx.get(i));
            }

            else if (file[i].getOriginalFilename().contains("elec")) {
                elecPricesService.setElecPrices(xlsx.get(i));
            }
        }
        xlsx.clear();
    }

    private List<XSSFWorkbook> open(MultipartFile[] file) {
        for(int i = 0; i < file.length; i++) {
            try (InputStream is = file[i].getInputStream()) { // try-with-resources
                xlsx.add(new XSSFWorkbook(is));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return xlsx;
    }


/*  Тестовый вариант метода, загружающий файлы с локального диска
    public void persistPricesDB() {
        String target = "D:/Works/AIG/Энергоресурсы/Gas/2023_AGC (rus) gas_v1_temp.xlsx";
        String target1 = "D:/Works/AIG/Энергоресурсы/Gas/2023_AGC (rus) elect_v1_temp.xlsx";
I
        try {
            xlsx = new XSSFWorkbook(new FileInputStream(target));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gasPricesService.setGasPrices(xlsx);

        try {
            xlsx = new XSSFWorkbook(new FileInputStream(target1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        elecPricesService.setElecPrices(xlsx);

    }
*/
}