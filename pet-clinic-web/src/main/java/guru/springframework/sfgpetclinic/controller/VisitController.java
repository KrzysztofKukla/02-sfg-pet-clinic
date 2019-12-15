package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.service.PetService;
import guru.springframework.sfgpetclinic.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

/**
 * @author Krzysztof Kukla
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class VisitController {
    private static final String VIEW_PETS_CREATE_OR_UPDATE_VISIT_FORM = "/pets/createOrUpdateVisitForm";

    private final VisitService visitService;
    private final PetService petService;

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }

    //it will be going to run with every request against this controller
    //this method will be load before each request method
    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);
        Visit visit = Visit.builder().build();
        visit.setPet(pet);
        model.addAttribute("pet", pet);
        return visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable Long petId, Model model) {
        return VIEW_PETS_CREATE_OR_UPDATE_VISIT_FORM;
    }

    @PostMapping("/owners/*/pets/{petId}/visits/new")
    public String processNewVisitForm(@PathVariable Long petId, @Valid Visit visit, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return VIEW_PETS_CREATE_OR_UPDATE_VISIT_FORM;
        } else {
            visitService.save(visit);
            return "redirect:/owners/*/pets/" + petId;
        }
    }

}
