package com.example.salon_kosmetyczny.services;

import com.example.salon_kosmetyczny.models.Pracownik;
import com.example.salon_kosmetyczny.models.Treatment;
import com.example.salon_kosmetyczny.repositories.PracownikRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PracownikService {

    Page<Pracownik> getAllPracowniks(Pageable pageable);

    List<Pracownik> getAllPracowniks2();

    Pracownik getPracownik(Long id);

    void savePracownik(Pracownik Pracownik);

    Pracownik getById(Long id);

    void delete(Long id);

    boolean exists(Long id);


}
