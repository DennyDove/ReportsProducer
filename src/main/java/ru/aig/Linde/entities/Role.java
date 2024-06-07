package ru.aig.Linde.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Так как это вызывает рекурсию с методом @ToString из класса Employee. Они ссылаются друг на друга.
// Для устранения рекурсии нужно самостоятельно переопределить методо toString(). См. ниже.

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "roles")
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    protected String Position;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    protected List<Employee> employee;

    public Role(Long id) {
        this.id = id;
    }

    // Самостоятельно переопределили данный метод, для устранения рекурсии
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", Position='" + Position + '\'' +
                '}';
    }
}

