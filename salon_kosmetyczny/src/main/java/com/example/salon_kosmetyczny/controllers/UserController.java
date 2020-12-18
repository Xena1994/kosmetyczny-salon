package com.example.salon_kosmetyczny.controllers;


import com.example.salon_kosmetyczny.models.User;
import com.example.salon_kosmetyczny.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by grzesiek on 23.08.2017.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/registrationForm.html")
    public String registration(Model model) {
        model.addAttribute("userCommand", new User());
        return "registrationForm";
    }

    @PostMapping("/registrationForm.html")
    public String registration(@Valid @ModelAttribute("userCommand") User userForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }
        userForm.setEnabled(true);
        userService.save(userForm);
        return "registrationSuccess";
    }

   //@GetMapping("/login")
   // public String login() {
     //   return "loginForm";
   // }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //aby użytkownik nie mógł sobie wstrzyknąć aktywacji konta oraz ról (np., ADMIN)
        //roles są na wszelki wypadek, bo warstwa serwisów i tak ustawia ROLE_USER dla nowego usera
        binder.setDisallowedFields("enabled", "roles");
    }
  //  @Controller
   // public class ForwardingController {
        //@GetMapping(value = {"loginForm" , "/login"})
        //public String forwardRequest() {
         //  return "forward:/";
        }
    //}


//}