package com.example.salon_kosmetyczny.services;



import com.example.salon_kosmetyczny.models.Treatment;
import com.example.salon_kosmetyczny.models.commands.TreatmentFilter;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Set;

public interface TreatmentService {
    Set<Treatment> getAllTreatments();

    Treatment save(Treatment treatment);

    Treatment getById(Long id);

    void delete(Long id);

    boolean exists(Long id);

    Set<Treatment> getSearch(TreatmentFilter search);

    List<Treatment> getAllTreatmentsList();

}
