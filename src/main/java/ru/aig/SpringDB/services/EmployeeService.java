package ru.aig.SpringDB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.aig.SpringDB.entities.Employee;
import ru.aig.SpringDB.entities.Role;
import ru.aig.SpringDB.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    protected EmployeeRepository employeeRepository;

    public Employee getById(Long id) {
        Optional<Employee> employee = employeeRepository.getEmployeeById(id);

        if (employee.isEmpty()) {
            throw new RuntimeException(String.format("Employee with id = %s, not found", id));
        }
        return employee.get();
    }

    public List<Employee> findAll() {
        List<Employee> employee = employeeRepository.findAll();
        return employee;
    }

    public Employee save(Employee employee) {
        employee.setRole(new Role(1L));
        return employeeRepository.save(employee);
        
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }





}
