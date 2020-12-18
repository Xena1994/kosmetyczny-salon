package com.example.salon_kosmetyczny.controllers;

import com.example.salon_kosmetyczny.models.Pracownik;
import com.example.salon_kosmetyczny.models.Treatment;
import com.example.salon_kosmetyczny.services.PracownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@SessionAttributes(names={})
public class PracownikListController {

    @Autowired
    private PracownikService pracownikService;

    @RequestMapping(path = "/users/add")
    public String getAllpracownicy3(Model model){
        model.addAttribute("pracownik",  pracownikService.getAllPracowniks2());
    return "users/pform";
    }

    @RequestMapping(value="/pracownicy", method = {RequestMethod.GET, RequestMethod.POST})
    public String showpracownikList(Model model, Pageable pageable){
        Page<Pracownik> pracowniks = pracownikService.getAllPracowniks(pageable);
        model.addAttribute("pracownicyListPage", pracowniks);
        return "/pracownicy/plist";
    }

    @Secured("IS_AUTHENTICATED_FULLY")
    @RequestMapping(value="/pracownicy/plist", params = "id", method = RequestMethod.GET)
    public String showpracownikDetails(Model model, Long id){
        //log.info("Pokazywanie szczegółów");
        model.addAttribute("pracownik", pracownikService.getPracownik(id));
        return "pracownikDetails";
    }

    

    @RequestMapping(value={"/pracownicy/add", "/pracownicy/edit"}, method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){
       Pracownik pracownik;


        if(id.isPresent()){
            Long pracownikId = id.get();
            model.addAttribute("action", "edit");
            pracownik =  pracownikService.getById(pracownikId);
        } else {
            model.addAttribute("action", "add");
            pracownik = new Pracownik();

        }

        model.addAttribute("pracownik",pracownik);

        return "/pracownicy/pform";
    }

    @RequestMapping(value={"/pracownicy/add2", "/pracownicy/edit2"}, method= RequestMethod.GET)
    public String showForm2(Model model, Optional<Long> id){
       Pracownik pracownik;


        if(id.isPresent()){
            Long pracownikId = id.get();
            model.addAttribute("action", "edit");
            pracownik =  pracownikService.getById(pracownikId);
        } else {
            model.addAttribute("action", "add");
            pracownik = new Pracownik();

        }

        model.addAttribute("pracownik",pracownik);

        return "/users/add";
    }

    @RequestMapping(value={"/pracownicy/add", "/pracownicy/edit"}, method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("pracownik") Pracownik pracownik, BindingResult errors){

//        if(errors.hasErrors()){
//            return "pracownicy/form";
//        }
        pracownikService.savePracownik(pracownik);
        return "redirect:/pracownicy";
    }


    @RequestMapping(value="/pracownicy/delete")
    public String delete(Model model, Long id){

        if(pracownikService.exists(id)){
            pracownikService.delete(id);
        }
        return "redirect:/pracownicy";
    }
    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        return queryString.substring(queryString.indexOf("&")+1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
    }

    @RequestMapping(value="/pracownicy/deactivate")
    public String deactivate(Model model, Long id){

        return "redirect:/pracownicy";
    }



}
