package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Pet;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

//    @Test
//    void processNewVisitForm() {
//        mockMvc.perform(MockMvcRequestBuilders.post("/owners/*/pets/{petId}/visits/new").)
//
//    }

}