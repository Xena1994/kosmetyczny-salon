package com.example.salon_kosmetyczny.repositories;

import com.example.salon_kosmetyczny.models.Pracownik;
import com.example.salon_kosmetyczny.models.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracownikRepository extends JpaRepository<Pracownik, Long> {
}
