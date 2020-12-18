package com.example.salon_kosmetyczny.services;


import com.example.salon_kosmetyczny.models.Treatment;
import com.example.salon_kosmetyczny.models.commands.TreatmentFilter;
import com.example.salon_kosmetyczny.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Override
    public Set<Treatment> getAllTreatments() {
        return new LinkedHashSet<>( treatmentRepository.findAll() );
    }

    @Override
    public Treatment save(Treatment treatment){
        return treatmentRepository.save(treatment);
    }

    @Override
    public Treatment getById(Long id) {
        return treatmentRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id){ treatmentRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id){
        return treatmentRepository.existsById(id);
    }

   @Override
    public Set<Treatment> getSearch(TreatmentFilter search) {
        if(search.isEmpty()){
            return new LinkedHashSet<>( treatmentRepository.findAll() );
        }else{
            return treatmentRepository.findAllTreatmentsUsingFilter(search.getPhraseLIKE(), search.getMinPrice(), search.getMaxPrice());
        }

    }


    @Override
    public List<Treatment> getAllTreatmentsList() {
        return treatmentRepository.findAll();
    }

}
