package ru.aig.SpringDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.aig.SpringDB.controllers.EmployeeController;
import ru.aig.SpringDB.entities.Employee;
import ru.aig.SpringDB.entities.Role;
import ru.aig.SpringDB.repositories.EmployeeRepository;
import ru.aig.SpringDB.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringWebApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringWebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringWebApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(EmployeeRepository employeeRepository, RoleRepository roleRepository,
                                  EmployeeController employeeController) {

        return args -> {
            log.info("Command line started");
//            log.info(employeeRepository.findByAge(23).toString());

            Employee employee1 = new Employee("Nastya", 18, "fitness");
            Employee employee2 = new Employee("Marina", 12, "puple");
            Employee employee3 = new Employee("Ksenia", 27, "fitness");

            List<Employee> employeeList = new ArrayList<>();

            employeeList.add(employee1);
            employeeList.add(employee2);
            employeeList.add(employee3);
            employeeRepository.saveAll(employeeList);

        };

    }

}
