package com.example.salon_kosmetyczny.services;


import com.example.salon_kosmetyczny.models.Pracownik;
import com.example.salon_kosmetyczny.models.User;
import com.example.salon_kosmetyczny.models.Visit;
import com.example.salon_kosmetyczny.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitRepository visitRepository;


    @Override
    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    @Override
    public List<Visit> getVisitsByPracownikId(Long pracownik_id) {
        return visitRepository.findVisitsByPracownik_Id(pracownik_id);
    }

    @Override
    public Visit getVisit(Long id) {
        Visit v = visitRepository.findById(id).orElse(null);
        return v;
    }

    @Override
    public void delete(Long id) {
        visitRepository.deleteById(id);
    }



    @Override
    public void add(Date visitDate, Pracownik pracownik, User dentist) {

    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public boolean exists(Long id) {
        return visitRepository.existsById(id);
    }
}
