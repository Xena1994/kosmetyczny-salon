package com.example.salon_kosmetyczny.repositories;


import com.example.salon_kosmetyczny.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
