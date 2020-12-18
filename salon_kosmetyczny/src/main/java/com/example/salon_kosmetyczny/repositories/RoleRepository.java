package com.example.salon_kosmetyczny.repositories;


import com.example.salon_kosmetyczny.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByType(Role.Types type);
}
