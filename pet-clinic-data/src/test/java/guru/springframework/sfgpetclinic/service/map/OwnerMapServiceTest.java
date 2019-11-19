package guru.springframework.sfgpetclinic.service.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.service.PetService;
import guru.springframework.sfgpetclinic.service.PetTypeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 * @author Krzysztof Kukla
 */
class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;

    @Mock
    private PetTypeService petTypeService;

    @Mock
    private PetService petService;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(petTypeService, petService);
    }

    @Test
    void findAll() {
        Owner owner1 = Owner.builder().id(1L).firstName("firstName").lastName("lastName").build();
        Owner owner2 = Owner.builder().id(2L).firstName("secondfirstName").lastName("secondlastName").build();
        ownerMapService.map.put(owner1.getId(), owner1);
        ownerMapService.map.put(owner2.getId(), owner2);

        Assertions.assertEquals(2, ownerMapService.findAll().size());
    }

    @Test
    void findById() {
        long id = 3L;
        Owner owner1 = Owner.builder().id(1L).firstName("firstName").lastName("lastName").build();
        Owner owner2 = Owner.builder().id(2L).firstName("secondfirstName").lastName("secondlastName").build();
        Owner owner3 = Owner.builder().id(3L).firstName("third firstName").lastName("third lastName").build();
        ownerMapService.map.put(owner1.getId(), owner1);
        ownerMapService.map.put(owner2.getId(), owner2);
        ownerMapService.map.put(owner3.getId(), owner3);

        Assertions.assertEquals(owner3, ownerMapService.findById(id));
    }

    @Test
    void findByIdDoesNotExist() {
        long id = 3L;
        Owner owner1 = Owner.builder().id(1L).firstName("firstName").lastName("lastName").build();
        Owner owner2 = Owner.builder().id(2L).firstName("secondfirstName").lastName("secondlastName").build();
        ownerMapService.map.put(owner1.getId(), owner1);
        ownerMapService.map.put(owner2.getId(), owner2);

        Assertions.assertNull(ownerMapService.findById(3L));
    }

    @Test
    void findByLastName() {
        String secondLastName = "secondLastName";
        Owner owner1 = Owner.builder().id(1L).firstName("firstName").lastName("lastName").build();
        Owner owner2 = Owner.builder().id(2L).firstName("secondfirstName").lastName(secondLastName).build();
        ownerMapService.map.put(owner1.getId(), owner1);
        ownerMapService.map.put(owner2.getId(), owner2);

        Assertions.assertEquals(owner2, ownerMapService.findByLastName(secondLastName));
    }

    @Test
    void findByLastNameDoesNotExist() {
        String secondLastName = "secondLastName";
        Owner owner1 = Owner.builder().id(1L).firstName("firstName").lastName("lastName").build();
        Owner owner2 = Owner.builder().id(2L).firstName("secondfirstName").lastName(secondLastName).build();
        ownerMapService.map.put(owner1.getId(), owner1);
        ownerMapService.map.put(owner2.getId(), owner2);

        Assertions.assertThrows(RuntimeException.class, () -> ownerMapService.findByLastName("invalid lastName"));
    }

    @Test
    void save() {
        //given
        org.assertj.core.api.Assertions.assertThat(ownerMapService.map).isEmpty();

        //when
        Owner owner2 = Owner.builder().firstName("secondfirstName").lastName("secondlastName").build();
        ownerMapService.save(owner2);

        //then
        org.assertj.core.api.Assertions.assertThat(ownerMapService.map).size().isEqualTo(1);
        Assertions.assertNotNull(ownerMapService.map.values().stream().findFirst().get().getId());
    }

    @Test
    void delete() {
        //given
        Owner owner1 = Owner.builder().id(1L).firstName("firstName").lastName("lastName").build();
        Owner owner2 = Owner.builder().id(2L).firstName("secondfirstName").lastName("second lastName").build();
        ownerMapService.map.put(owner1.getId(), owner1);
        ownerMapService.map.put(owner2.getId(), owner2);
        org.assertj.core.api.Assertions.assertThat(ownerMapService.map).containsValue(owner2);

        //when
        ownerMapService.delete(owner2);

        //then
        org.assertj.core.api.Assertions.assertThat(ownerMapService.map).doesNotContainValue(owner2);

    }

    @Test
    void deleteById() {
        //given
        Owner owner1 = Owner.builder().id(1L).firstName("firstName").lastName("lastName").build();
        Owner owner2 = Owner.builder().id(2L).firstName("secondfirstName").lastName("second lastName").build();
        ownerMapService.map.put(owner1.getId(), owner1);
        ownerMapService.map.put(owner2.getId(), owner2);
        org.assertj.core.api.Assertions.assertThat(ownerMapService.map).containsValue(owner2);

        //when
        ownerMapService.deleteById(owner2.getId());

        //then
        org.assertj.core.api.Assertions.assertThat(ownerMapService.map).doesNotContainValue(owner2);

    }

}