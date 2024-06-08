package ru.aig.Linde.services;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aig.Linde.entities.ElecPrices;
import ru.aig.Linde.repositories.ElecPricesRepository;

import java.util.List;

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















