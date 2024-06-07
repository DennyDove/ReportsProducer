package ru.aig.Linde.services;

//import jakarta.persistence.EntityManager;
//import jakarta.transaction.Transactional;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aig.Linde.entities.ElecPrices;
import ru.aig.Linde.entities.GasPrices;
import ru.aig.Linde.repositories.GasPricesRepository;

import java.util.List;

@Service
public class GasPricesService {

    @Autowired
    private GasPricesRepository gasPricesRepository;

    List<GasPrices> gasPrices;

    public void setGasPrices(XSSFWorkbook gas_xlsx) {
        gasPrices = gasPricesRepository.findAll();
        if (gasPrices.isEmpty()) {
            for (int i = 0; i < 12; i++) {
                GasPrices gasPrice = new GasPrices(gas_xlsx.getSheetAt(1).getRow(20).getCell(4 + i).getNumericCellValue());
                gasPrices.add(gasPrice);
            }
            gasPricesRepository.saveAll(gasPrices);
            gasPrices.clear();
        } else {
            for (int i = 0; i < 12; i++) {
                gasPrices.get(i).setPrices(gas_xlsx.getSheetAt(1).getRow(20).getCell(4 + i).getNumericCellValue());
            }
            gasPricesRepository.saveAll(gasPrices);
            gasPrices.clear();
        }
    }

    public double getPriceByMonth(int month) {
        gasPrices = gasPricesRepository.findAll();
        return gasPrices.get(month).getPrices();
    }
}