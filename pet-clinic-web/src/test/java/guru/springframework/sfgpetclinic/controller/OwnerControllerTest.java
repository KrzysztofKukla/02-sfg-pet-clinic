package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.service.OwnerService;
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

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    private OwnerService ownerService;

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
            .andExpect(MockMvcResultMatchers.view().name("owners/index"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("owners"))
            .andExpect(MockMvcResultMatchers.model().attribute("owners", owners))
            .andExpect(MockMvcResultMatchers.model().attribute("owners", hasSize(1)));
        BDDMockito.then(ownerService).should().findAll();
    }

    @Test
    void find() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("notimplemented"));
        BDDMockito.then(ownerService).shouldHaveNoInteractions();
    }

}