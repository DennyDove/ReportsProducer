package ru.aig.Linde;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.aig.Linde.controllers.MainController;
import ru.aig.Linde.entities.Employee;
import ru.aig.Linde.repositories.EmployeeRepository;
import ru.aig.Linde.repositories.GasPricesRepository;
import ru.aig.Linde.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ReportsProducer {

    private static final Logger log = LoggerFactory.getLogger(ReportsProducer.class);

    public static void main(String[] args) {
        SpringApplication.run(ReportsProducer.class, args);
    }

    @Bean
    public CommandLineRunner demo(EmployeeRepository employeeRepository, RoleRepository roleRepository,
                                  MainController mainController, GasPricesRepository gasPricesRepository) {

        return args -> {
            log.info("Command line started");
            log.info("");

            Employee employee1 = new Employee("Anjutka Shirokowa", 23, "Stomatology");
            List<Employee> employeeList = new ArrayList<>();
            employeeList.add(employee1);

            employeeRepository.saveAll(employeeList);

        };
    }
}
