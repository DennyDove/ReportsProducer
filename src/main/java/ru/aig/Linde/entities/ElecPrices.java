package ru.aig.Linde.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "elec_prices")
public class ElecPrices {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Integer id;
    protected Double prices;

    public ElecPrices(double elecPrices) {
        this.prices = elecPrices;
    }


}
