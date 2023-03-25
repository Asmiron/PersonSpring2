package com.yaram.Spring.Controlers;


import com.yaram.Spring.DAO.PeopleDAO;
import com.yaram.Spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleControler {
    private final PeopleDAO peopleDAO;

    @Autowired
    public PeopleControler(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", peopleDAO.GetAll());

        return "People/people_index";
    }

    @GetMapping("/{id}")
    public String Show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleDAO.Show(id));
        return "People/person_show";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "People/new";
    }

    @PostMapping()
    public String Create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "People/new";
        peopleDAO.Create(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String Edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleDAO.Show(id));
        return "People/edit";
    }

    @PatchMapping("/{id}")
    public String Update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "People/edit";
        peopleDAO.Update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String Delete(@PathVariable("id") int id){
        peopleDAO.Delete(id);
        return "redirect:/people";
    }
}
