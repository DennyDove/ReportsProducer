package ru.aig.Linde.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aig.Linde.entities.GasPrices;

import java.util.List;
import java.util.Optional;

@Repository
public interface GasPricesRepository extends JpaRepository <GasPrices, Integer> {

}
