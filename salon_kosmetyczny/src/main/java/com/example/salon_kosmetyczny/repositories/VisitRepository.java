package com.example.salon_kosmetyczny.repositories;


import com.example.salon_kosmetyczny.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query(
            "select v from Visit v where v.user.id = :user"
    )

    List<Visit> findAllByUserId(@Param("user") Long id);

    List<Visit> findVisitsByPracownik_Id(Long pracownik_id);

}
