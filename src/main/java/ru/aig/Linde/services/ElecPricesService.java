package ru.aig.Linde.services;

//import jakarta.persistence.EntityManager;
//import jakarta.transaction.Transactional;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.aig.Linde.entities.ElecPrices;
import ru.aig.Linde.repositories.ElecPricesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ElecPricesService {

    @Autowired
    private ElecPricesRepository elecPricesRepository;

    List<ElecPrices> elecPrices;

    public void setElecPrices(XSSFWorkbook elec_xlsx) {
        elecPrices = elecPricesRepository.findAll();
        if (elecPrices.isEmpty()) {
            for (int i = 0; i < 12; i++) {
                ElecPrices elecPrice = new ElecPrices(elec_xlsx.getSheetAt(1).getRow(15).getCell(3 + i).getNumericCellValue());
                elecPrices.add(elecPrice);
            }
            elecPricesRepository.saveAll(elecPrices);
            elecPrices.clear();
        } else {
            for (int i = 0; i < 12; i++) {
                elecPrices.get(i).setPrices(elec_xlsx.getSheetAt(1).getRow(15).getCell(3 + i).getNumericCellValue());
            }
            elecPricesRepository.saveAll(elecPrices);
            elecPrices.clear();
        }
    }

    public double getPriceByMonth(int month) {
        List<ElecPrices> elecPrices = elecPricesRepository.findAll();
        return elecPrices.get(month).getPrices();
    }
}















