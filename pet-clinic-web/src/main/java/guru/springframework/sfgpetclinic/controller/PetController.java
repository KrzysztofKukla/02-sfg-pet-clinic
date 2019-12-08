package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.service.OwnerService;
import guru.springframework.sfgpetclinic.service.PetService;
import guru.springframework.sfgpetclinic.service.PetTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Controller
@RequestMapping("/owners/{ownerId}")
@RequiredArgsConstructor
@Slf4j
public class PetController {
    public static final String VIEW_PETS_CREATE_OR_UPDATE_PET_FORM = "/pets/createOrUpdatePetForm";
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;
    private final PetService petService;

    /**
     * to avoid populate types and owner in every controller method we can do that like here
     * types and owner attribute will ba available in Model on Thymeleaf in each method in this controller
     *
     * @return types, owner
     */
    @ModelAttribute("types")
    public Set<PetType> populatePetType() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

//    @GetMapping("/pets/new")
//    public String initPetForm(@PathVariable Long ownerId, Model model) {
//        model.addAttribute("pet", Pet.builder().build());
//        return "/owners/" + ownerId + "/pets/createOrUpdatePetForm";
//    }

    @GetMapping("/pets/new")
    public String initPetForm(Owner owner, Model model) {
        Pet pet = Pet.builder().build();
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEW_PETS_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/new")
    public String processPetForm(Owner owner, @Valid Pet pet, BindingResult bindingResult, Model model) {
        String petName = pet.getName();
        if (StringUtils.isNotBlank(petName) && pet.isNew() && owner.getPet(petName, true) != null) {
            bindingResult.rejectValue("name", "duplicate", "Already exists");
        }
//        owner.getPets().add(pet);
        if (bindingResult.hasErrors()) {
            return VIEW_PETS_CREATE_OR_UPDATE_PET_FORM;
        } else {
            pet.setOwner(owner);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdatePetForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return VIEW_PETS_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdatePetForm(Owner owner, @Valid Pet pet, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEW_PETS_CREATE_OR_UPDATE_PET_FORM;
        } else {
            pet.setOwner(owner);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
