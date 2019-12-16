package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Controller
@RequestMapping("/vets")
@RequiredArgsConstructor
public class VetController {
    private final VetService vetService;

    @RequestMapping({"", "/index"})
    public String listVets(Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

    @GetMapping("/api/vets")
    @ResponseBody
    public Set<Vet> getVetsJson() {
        return vetService.findAll();
    }

}
