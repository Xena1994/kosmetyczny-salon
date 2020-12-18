package com.example.salon_kosmetyczny.config;


import com.example.salon_kosmetyczny.models.Pracownik;
import com.example.salon_kosmetyczny.models.Role;
import com.example.salon_kosmetyczny.models.Treatment;
import com.example.salon_kosmetyczny.models.User;
import com.example.salon_kosmetyczny.repositories.RoleRepository;
import com.example.salon_kosmetyczny.repositories.PracownikRepository;
import com.example.salon_kosmetyczny.repositories.TreatmentRepository;
import com.example.salon_kosmetyczny.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    TreatmentRepository treatmentRepository;
    @Autowired
    PracownikRepository pracowniktRepository;

    @Bean
    InitializingBean init() {

        return () -> {
            if(treatmentRepository.findAll().isEmpty()){

                Treatment treatment = new Treatment();
                treatment.setName("Malowanie Paznokci");
                treatment.setPrice(new BigDecimal(30));
                treatmentRepository.save(treatment);

                Treatment treatment2 = new Treatment();
                treatment2.setName("depilacja");
                treatment2.setPrice(new BigDecimal(40));
                treatmentRepository.save(treatment2);

            }
            if(pracowniktRepository.findAll().isEmpty()) {
                Pracownik pracownik = new Pracownik();
                pracownik.setFirstName("Jan");
                pracownik.setLastName("Kowalski");
            }

            if(roleRepository.findAll().isEmpty()){
                try {
                    Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                    Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_ADMIN));

                    User user = new User("user", true);
                    user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                    user.setPassword(passwordEncoder.encode("user"));

                    User admin = new User("admin", true);
                    admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                    admin.setPassword(passwordEncoder.encode("admin"));

                    User test = new User("test", true);
                    test.setRoles(new HashSet<>(Arrays.asList(roleAdmin, roleUser)));
                    test.setPassword(passwordEncoder.encode("test"));

                    userRepository.save(user);
                    userRepository.save(admin);
                    userRepository.save(test);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        };
    }

}
