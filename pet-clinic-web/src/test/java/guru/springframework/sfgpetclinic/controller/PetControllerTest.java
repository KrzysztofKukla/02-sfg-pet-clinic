package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.service.OwnerService;
import guru.springframework.sfgpetclinic.service.PetService;
import guru.springframework.sfgpetclinic.service.PetTypeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    private PetTypeService petTypeService;

    @Mock
    private OwnerService ownerService;

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    private MockMvc mockMvc;

    Owner owner;
    Set<PetType> types;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();

        owner = Owner.builder().id(1L).build();
        BDDMockito.when(ownerService.findById(anyLong())).thenReturn(owner);

        types = createPetTypes();
        BDDMockito.when(petTypeService.findAll()).thenReturn(types);

    }

    @Test
    void initPetFormTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/new"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("pet", Matchers.any(Pet.class)))
            .andExpect(view().name("/pets/createOrUpdatePetForm"));
        BDDMockito.then(petService).shouldHaveNoInteractions();
    }

    @Test
    void processPetFormNoErrorsTest() throws Exception {
        Pet pet = Pet.builder().id(1L).build();

        BDDMockito.when(petService.save(ArgumentMatchers.any(Pet.class))).thenReturn(pet);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/new"))
            .andExpect(status().is3xxRedirection())
            .andExpect(model().hasNoErrors())
            .andExpect(view().name("redirect:/owners/1"));
        BDDMockito.then(petService).should().save(ArgumentMatchers.any(Pet.class));
    }

    @Test
    void initUpdatePetFormTest() throws Exception {
        Pet pet = Pet.builder().id(1L).build();
        BDDMockito.when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/1/edit"))
            .andExpect(model().attribute("pet", Matchers.any(Pet.class)))
            .andExpect(view().name("/pets/createOrUpdatePetForm"));
        BDDMockito.then(petService).should().findById(anyLong());
    }

    @Test
    void processUpdatePetFormNoErrorsTest() throws Exception {
        Pet savedPet = Pet.builder().id(1L).build();
        BDDMockito.when(petService.save(ArgumentMatchers.any(Pet.class))).thenReturn(savedPet);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/petId/edit"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/1"));
        BDDMockito.then(petService).should().save(ArgumentMatchers.any(Pet.class));
    }

    private Set<PetType> createPetTypes() {
        PetType dog = PetType.builder().id(1L).name("Dog").build();
        PetType cat = PetType.builder().id(2L).name("Cat").build();
        Set<PetType> types = new HashSet<>();
        types.add(dog);
        types.add(cat);
        return types;
    }

}