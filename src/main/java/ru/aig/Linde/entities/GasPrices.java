package ru.aig.Linde.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "gas_prices")
public class GasPrices {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Integer id;
    protected Double prices;

    public GasPrices(double prices) {
        this.prices = prices;
    }


}
