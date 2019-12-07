package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Krzysztof Kukla
 */
@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {
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

    @RequestMapping({"", "/index"})
    public String ownersList(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping("/find")
    public String find() {
        return "notimplemented";
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

}
