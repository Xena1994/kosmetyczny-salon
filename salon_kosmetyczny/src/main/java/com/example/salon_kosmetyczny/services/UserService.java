package com.example.salon_kosmetyczny.services;


import com.example.salon_kosmetyczny.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void save(User user);

    Page<User> getAllUsers(Pageable pageable);
    List<User> getAllUsers();

    User getUser(Long id);

    User getRole(Long id);

    void saveUser(User user);

    User getById(Long id);

    void delete(Long id);

    boolean exists(Long id);

    boolean isUniqueLogin(String login);

    User getUserByUsername(String username);
}
