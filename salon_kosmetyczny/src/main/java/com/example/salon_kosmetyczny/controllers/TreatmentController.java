package com.example.salon_kosmetyczny.controllers;


import com.example.salon_kosmetyczny.models.Treatment;
import com.example.salon_kosmetyczny.models.commands.TreatmentFilter;
import com.example.salon_kosmetyczny.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class TreatmentController {
    @Autowired
    TreatmentService treatmentService;

    @GetMapping("/treatments")
    public String showList(Model model) {
        model.addAttribute("treatments", treatmentService.getAllTreatments());
        model.addAttribute("searchCommand", new TreatmentFilter());
        return "treatments/list";
    }

    @PostMapping("/treatments")
    public String searchList(Model model, @Valid @ModelAttribute("searchCommand")TreatmentFilter tf){
        model.addAttribute("treatments",treatmentService.getSearch(tf));
        return "treatments/list";
    }

    @GetMapping("/treatments/details/{id}")
    public String showDetails(Model model, @PathVariable Long id){
        model.addAttribute(model.addAttribute("treatment", treatmentService.getById(id)));
        return "treatments/details";
    }

    @RequestMapping(value={"/treatments/add", "/treatments/edit"}, method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){
        Treatment treatment;
        if(id.isPresent()){
            Long treatmentId = id.get();
            model.addAttribute("action", "edit");
            treatment = treatmentService.getById(treatmentId);
        } else {
            model.addAttribute("action", "add");
            treatment = new Treatment();


        }
        model.addAttribute("treatment",treatment);

        return "treatments/form";
    }

    @RequestMapping(value={"/treatments/add", "/treatments/edit"}, method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("treatment") Treatment treatment, BindingResult errors){
        if(errors.hasErrors()){
            return "treatments/form";
        }
        treatmentService.save(treatment);
        return "redirect:/treatments";
    }

    @RequestMapping(value="/treatments/delete")
    public String delete(Model model, Long id){

        if(treatmentService.exists(id)){
            treatmentService.delete(id);
        }
        return "redirect:/treatments";
    }
}
