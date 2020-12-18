package com.example.salon_kosmetyczny.services;



import com.example.salon_kosmetyczny.models.Pracownik;
import com.example.salon_kosmetyczny.models.User;
import com.example.salon_kosmetyczny.models.Visit;

import java.util.Date;
import java.util.List;

public interface VisitService {

    List<Visit> getAllVisits();

    List<Visit>  getVisitsByPracownikId(Long patient_id);

    Visit getVisit(Long id);

    void delete(Long id);

    void add(Date visitDate, Pracownik pracownik, User dentist);

    Visit save(Visit visit);

    boolean exists(Long id);
}
