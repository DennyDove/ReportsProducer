package ru.aig.SpringDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.aig.SpringDB.entities.Role;
import ru.aig.SpringDB.repositories.EmployeeRepository;
import ru.aig.SpringDB.repositories.RoleRepository;

import java.util.Optional;

@SpringBootApplication
public class SpringWebApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringWebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringWebApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(EmployeeRepository employeeRepository, RoleRepository roleRepository) {

        return args -> {
            log.info("Command line started");
//            log.info(employeeRepository.findByAge(23).toString());

        };

    }

}
