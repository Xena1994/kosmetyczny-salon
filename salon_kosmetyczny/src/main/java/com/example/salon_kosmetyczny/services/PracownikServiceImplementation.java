package com.example.salon_kosmetyczny.services;


import com.example.salon_kosmetyczny.models.Pracownik;
import com.example.salon_kosmetyczny.models.Treatment;
import com.example.salon_kosmetyczny.repositories.PracownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PracownikServiceImplementation implements PracownikService {


    @Autowired
    private PracownikRepository pracownikRepository;


    @Override
    public Page<Pracownik> getAllPracowniks(Pageable pageable) {
        return pracownikRepository.findAll(pageable);

    }

    @Override
    public List<Pracownik> getAllPracowniks2() {
        return pracownikRepository.findAll();

    }

    @Override
    public Pracownik getPracownik(Long id) {
        return pracownikRepository.getOne(id);
    }


    @Override
    public void savePracownik(Pracownik Pracownik) {
        pracownikRepository.save(Pracownik);
    }
    @Override
    public Pracownik getById(Long id) {
        return pracownikRepository.getOne(id);
    }

    @Override
    public void delete(Long id){ pracownikRepository.delete(pracownikRepository.getOne(id)); }

    @Override
    public boolean exists(Long id){
        return pracownikRepository.existsById(id);
    }
}
