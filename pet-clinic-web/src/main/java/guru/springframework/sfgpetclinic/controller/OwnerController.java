package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Krzysztof Kukla
 */
@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {
    private static final String VIEWS_OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "/owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;

    /**
     * @InitBinder is very old Spring stuff and is not used in modern technology application
     * it allows to configuration binding variables from Java to Html especially like post form
     **/
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        /**
         * here we don't want bind 'id' property because security wise
         * we want to avoid 'id' field access to user, because he could change that in database
         */
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"/index"})
    public String ownersList(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping("/find")
    public String find(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    /**
     * Custom handler for displaying an owner.
     *
     * @param id the ID of the owner to display
     * @return a ModelMap with the model attributes for the view
     */
    @GetMapping("/{id}")
    /* ModelAndView is like composite object
    it creates Model and View with a path
    so it does the same what we done previously
     */
    public ModelAndView showOwner(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/owners/ownerDetails");

        //model.add attribute("owner", Owner object)
        modelAndView.addObject(ownerService.findById(id));
        return modelAndView;
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }
        List<Owner> result = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        if (result.isEmpty()) {
            bindingResult.rejectValue("lastName", "Not found", "Not found");
            return "owners/findOwners";
        }
        if (result.size() == 1) {
            Long id = result.stream().findFirst().get().getId();
            return "redirect:/owners/" + id;
        } else {
            model.addAttribute("selections", result);
            return "owners/ownersList";
        }
    }

    @GetMapping("/new")
    public String initOwnerForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/new")
    //@Valid we are running validation on Owner
    public String processOwnerForm(@Valid Owner owner, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return VIEWS_OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
        } else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@PathVariable Long ownerId, @Valid Owner owner, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return VIEWS_OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
        } else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
