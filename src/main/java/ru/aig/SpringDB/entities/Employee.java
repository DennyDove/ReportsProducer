package ru.aig.SpringDB.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "employees")
@ToString
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    protected String name;
    protected Integer age;
    protected String profession;
    protected String hobby;
    protected String email;

    @ManyToOne
    protected Role role;

    public Employee(String name, Integer age, String profession) {
        this.name = name;
        this.age = age;
        this.profession = profession;
    }
}
