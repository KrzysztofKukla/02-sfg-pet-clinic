package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.service.PetService;
import guru.springframework.sfgpetclinic.service.VisitService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    private VisitService visitService;
    @Mock
    private PetService petService;

    @InjectMocks
    private VisitController visitController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();

        Pet pet = Pet.builder().id(1L).build();
        BDDMockito.when(petService.findById(anyLong())).thenReturn(pet);
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/1/visits/new"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("pet", Matchers.any(Pet.class)))
            .andExpect(view().name("/pets/createOrUpdateVisitForm"));
        BDDMockito.then(petService).should(BDDMockito.times(1)).findById(anyLong());
    }

    @Test
    void processNewVisitFormNoErrors() throws Exception {
        Visit visit = Visit.builder().id(1L).build();
        BDDMockito.when(visitService.save(any(Visit.class))).thenReturn(visit);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("description", "first description");
        params.add("date", "2018-11-11");

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/*/pets/1/visits/new")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .params(params)
        )
            .andExpect(model().hasNoErrors())
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/*/pets/1"));
        BDDMockito.then(visitService).should().save(any(Visit.class));

    }

    @Test
    void processNewVisitFormHasErrors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/owners/*/pets/1/visits/new")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
            .andExpect(model().hasErrors())
            .andExpect(model().attributeHasFieldErrors("visit", "description"))
            .andExpect(status().isOk())
            .andExpect(view().name("/pets/createOrUpdateVisitForm"));
        BDDMockito.then(visitService).shouldHaveNoInteractions();
    }

}