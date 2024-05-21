package ru.aig.SpringDB.repositories;

import java.util.List;
import java.util.Optional;

import ru.aig.SpringDB.entities.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {

    List<Employee> findByAge(Integer age);

    Optional<Employee> getEmployeeById(Long id);

    @Query(value = "select e from Employee e where e.age = :age and e.email like %:keyword%")
    List<Employee> findByAgeAndSeq(@Param("age") Integer age, @Param("keyword") String keyword);



}
