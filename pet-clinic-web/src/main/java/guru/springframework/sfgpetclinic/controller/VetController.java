package guru.springframework.sfgpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Krzysztof Kukla
 */
@Controller
@RequestMapping("/vets")
public class VetController {
    @RequestMapping({"", "/index"})
    public String listVets() {
        return "vets/index";
    }

}
