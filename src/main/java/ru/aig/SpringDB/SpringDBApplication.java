/*
package ru.aig.SpringDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.aig.SpringDB.entities.Employee;
import ru.aig.SpringDB.entities.Role;
import ru.aig.SpringDB.repositories.EmployeeRepository;
import ru.aig.SpringDB.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@SpringBootApplication
public class SpringDBApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringDBApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringDBApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(EmployeeRepository employeeRepository, RoleRepository roleRepository) {

        return args -> {
            log.info("Command line started");

//            log.info(employeeRepository.findByAge(23).toString());
/*
            Employee employee = new Employee("Masha", 21, "marketing");
            Employee employee1 = new Employee("Nastya", 18, "fitness");
            Employee employee2 = new Employee("Marina", 12, "puple");
            Employee employee3 = new Employee("Ksenia", 27, "fitness");
 */

//          Если добавлять employees в список данным методом, то если в таблице DB уже имеются строковые записи, то id ставится некорректно
//          List<Employee> employeeList = List.of(employee, employee1, employee2, employee3);

/*
            List<Employee> employeeList = new ArrayList<>();

            employeeList.add(employee1);
            employeeList.add(employee2);
            employeeRepository.saveAll(employeeList);
*/
//          List<Employee> employeeList = employeeRepository.findAll();
/*
            Optional<Employee> employee1 =  employeeRepository.findById(53L);
            employee1.get().setAge(23);
            employee1.get().setEmail("nastya@big_tits.ru");
            employeeRepository.save(employee1.get());
*/
            /*
            List<Employee> employeeList = employeeRepository.findByAgeAndSeq(23, "tits");
            log.info(employeeList.toString());

            Role role = new Role("ADMIN");
            Role role1 = new Role("USER");
            Role role2 = new Role("SUPERVISOR");

            List<Role> roleList = List.of(role, role1, role2);

            roleRepository.saveAll(roleList);


            List<Role> roleList = roleRepository.findAll();
            log.info(roleList.toString());

        };

    }

}
*/