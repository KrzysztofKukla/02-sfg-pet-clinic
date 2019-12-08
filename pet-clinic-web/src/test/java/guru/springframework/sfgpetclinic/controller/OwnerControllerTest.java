package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.service.OwnerService;
import org.assertj.core.util.Lists;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    private OwnerService ownerService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private OwnerController ownerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void ownersList() throws Exception {
        //given
        Owner owner = Owner.builder().id(1L).lastName("lastName").build();
        Set<Owner> owners = new HashSet<>();
        owners.add(owner);

        //when
        BDDMockito.when(ownerService.findAll()).thenReturn(owners);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/index"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(view().name("owners/index"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("owners"))
            .andExpect(MockMvcResultMatchers.model().attribute("owners", owners))
            .andExpect(MockMvcResultMatchers.model().attribute("owners", hasSize(1)));
        BDDMockito.then(ownerService).should().findAll();
    }

    @Test
    void find() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(model().attribute("owner", Matchers.any(Owner.class)))
            .andExpect(view().name("owners/findOwners"));
        BDDMockito.then(ownerService).shouldHaveNoInteractions();
    }

    @Test
    void showOwnerTest() throws Exception {
        Owner owner = Owner.builder().id(5L).build();
        BDDMockito.when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("/owners/ownerDetails"))
            .andExpect(model().attribute("owner", Matchers.any(Owner.class)))
            .andExpect(model().attribute("owner", Matchers.hasProperty("id", is(5L))));

        BDDMockito.then(ownerService).should().findById(anyLong());
    }

    @Test
    void processFindFormEmptyResult() throws Exception {
        BDDMockito.when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Lists.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.model().hasErrors())
            .andExpect(model().attributeHasFieldErrorCode("owner", "lastName", "Not found"))
            .andExpect(view().name("owners/findOwners"));
        BDDMockito.then(ownerService).should().findAllByLastNameLike(anyString());
    }

    @Test
    void processFindFormEmptyResultTestOnlyOneResult() throws Exception {
        Owner owner1 = Owner.builder().id(1L).lastName("John").build();
        List<Owner> ownersList = Arrays.asList(owner1);

        BDDMockito.when(ownerService.findAllByLastNameLike(anyString())).thenReturn(ownersList);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
            .andExpect(status().is3xxRedirection())
            .andExpect(model().hasNoErrors())
            .andExpect(view().name("redirect:/owners/1"));
        BDDMockito.then(ownerService).should().findAllByLastNameLike(anyString());
    }

    @Test
    void processFindFormEmptyResultTestManyItemsResult() throws Exception {
        String lastName = "John";
        Owner owner1 = Owner.builder().id(1L).lastName(lastName).build();
        Owner owner2 = Owner.builder().id(2L).lastName(lastName).build();
        List<Owner> ownersList = Arrays.asList(owner1, owner2);

        BDDMockito.when(ownerService.findAllByLastNameLike(anyString())).thenReturn(ownersList);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("selection", Matchers.any(List.class)))
            .andExpect(model().hasNoErrors())
            .andExpect(view().name("owners/ownersList"));
        BDDMockito.then(ownerService).should().findAllByLastNameLike(anyString());
    }

}