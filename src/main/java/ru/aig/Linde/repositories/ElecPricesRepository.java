package ru.aig.Linde.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aig.Linde.entities.ElecPrices;

import java.util.Optional;

@Repository
public interface ElecPricesRepository extends JpaRepository <ElecPrices, Integer> {

    @Override
    Optional<ElecPrices> findById(Integer id);
}
