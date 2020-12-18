package com.example.salon_kosmetyczny.controllers;

import com.example.salon_kosmetyczny.exceptions.UserNotFoundException;
import com.example.salon_kosmetyczny.exceptions.GeneralNotFoundException;
import com.example.salon_kosmetyczny.models.Pracownik;
import com.example.salon_kosmetyczny.models.Treatment;
import com.example.salon_kosmetyczny.models.User;
import com.example.salon_kosmetyczny.models.Visit;

import com.example.salon_kosmetyczny.repositories.TreatmentRepository;
import com.example.salon_kosmetyczny.repositories.UserRepository;
import com.example.salon_kosmetyczny.repositories.VisitRepository;
import com.example.salon_kosmetyczny.services.PracownikService;
import com.example.salon_kosmetyczny.services.UserService;
import com.example.salon_kosmetyczny.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class VisitController {

    @Autowired
    VisitRepository visitRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    VisitService visitService;
    @Autowired
    PracownikService pracownikService;
    @Autowired
    TreatmentRepository treatmentRepository;
   // @Autowired
  //  VisitService visitService;

    @GetMapping("/visits")
    public String showVisitsList(Model m){
        m.addAttribute("visitsList", visitService.getAllVisits());

        return "visits/list";
    }

    @GetMapping("/myVisits")
    public String showMyVisits(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long id;
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            model.addAttribute("visits", visitRepository.findAllByUserId(
                    userRepository.findByUsername(user.getUsername()).getId()
            ));
        }
        else{
            model.addAttribute("visits", new LinkedList<>());
        }
        return "visits/myVisits";
    }

    @RequestMapping(value ={ "/visits/add", "/visits/edit"},method = RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){
        Visit visit;
        if(id.isPresent()){
            Long visitId = id.get();
            model.addAttribute("action","Edytuj wizytę");
            visit = visitService.getVisit(visitId);
        }else{
            model.addAttribute("action", "Dodaj wizytę");
            visit = new Visit();
        }
        model.addAttribute("visit",visit);
        model.addAttribute("pracownicyList", pracownikService.getAllPracowniks2());
        model.addAttribute("UserList", userService.getAllUsers());
        return "visits/form";
    }

    @RequestMapping(value={"/visits/form"}, method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("visit") Visit visit, BindingResult error){
        System.out.println(visit.getUser().getId());
        System.out.println(visit.getUser().getUsername());
        visitService.save(visit);
        Pracownik p = pracownikService.getById(visit.getPracownik().getId());
       // User d = userService.getOne(visit.getPracownik().getId()).orElseThrow(DentistNotFoundException::new);
     //  User d = userService.getUser(visit.getPracownik().getId()).orElseThrow(UserNotFoundException::new);
      //  try {

            userService.getUser(visit.getPracownik().getId());

       // }catch (UserNotFoundException e){
      //      throw new UserNotFoundException();
      //  }
        return "redirect:/visits";
    }

    @RequestMapping(path = "/visits/delete")
    public String delete(Model model, Long id){
        if(visitService.exists(id)){
            Visit v = visitService.getVisit(id);

            visitService.delete(id);
        }
        return "redirect:/visits";
    }

    @GetMapping("/visits/{id}")
    public String showDetails(Model model, @PathVariable Long id){
        model.addAttribute("visit", visitService.getVisit(id));
        return "visits/details";
    }

    @GetMapping("/visits/{id}/addTreatment")
    public String showAddTreatment(Model model, @PathVariable Long id){
        model.addAttribute("visit", visitService.getVisit(id));
        model.addAttribute("treatments", treatmentRepository.findAll());
        model.addAttribute("treatment", new Treatment());
        return "visits/addTreatment";
    }

    @PostMapping("/visits/{id}/addTreatment")
    public String addTreatment(Model model, @PathVariable Long id, @ModelAttribute("treatment") Treatment treatment){
        Visit v = visitService.getVisit(id);
        List<Treatment> treatments = ( v.getTreatments() == null ? new LinkedList<>() : v.getTreatments() );
        treatments.add(treatmentRepository.findById(treatment.getId()).orElseThrow(() -> new GeneralNotFoundException("Nie ma takiego zabiegu")));
        v.setTreatments(treatments);
        v.setTotal(v.calculatePrice());
        visitService.save(v);
        return "redirect:/visits/"+id;
    }

    @GetMapping("/visits/{id}/processPayment")
    public String processPayment(@PathVariable Long id){
        Visit v = visitService.getVisit(id);
        v.setFinished(true);
        visitRepository.save(v);
        return "redirect:/visits/"+id;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, false);
        binder.registerCustomEditor(Date.class, "visitDate", dateEditor);

        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        binder.registerCustomEditor(Float.class, "price", new CustomNumberEditor(Float.class, numberFormat, false));



    }
}
