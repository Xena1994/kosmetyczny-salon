package com.example.salon_kosmetyczny.repositories;


import com.example.salon_kosmetyczny.models.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.Set;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    @Query("SELECT v FROM Treatment v WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR "+
            "upper(v.name) LIKE upper(:phrase)" +
            ") " +
            "AND " +
            "(:min is null OR :min <= v.price) " +
            "AND (:max is null OR :max >= v.price)")
    Set<Treatment> findAllTreatmentsUsingFilter(@Param("phrase") String p, @Param("min") Float priceMin, @Param("max") Float priceMax);
}
