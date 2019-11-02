package guru.springframework.sfgpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Krzysztof Kukla
 */
@Controller
@RequestMapping("/owners")
public class OwnerController {
    @RequestMapping({"", "/index"})
    public String ownersList() {
        return "owners/index";
    }

}
