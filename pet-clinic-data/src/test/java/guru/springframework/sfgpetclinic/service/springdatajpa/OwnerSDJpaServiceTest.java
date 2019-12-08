package guru.springframework.sfgpetclinic.service.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repository.OwnerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
//this setup tests as Mockito
@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @InjectMocks
    //this have to be implementation
    private OwnerSDJpaService ownerService;

    @Mock
    private OwnerRepository ownerRepository;

    @BeforeEach
    void setUp() {
        //not needed, because we have @ExtendWith(MockitoExtension.class) & @InjectMocks
//        MockitoAnnotations.initMocks(this);
//        ownerService = new OwnerSDJpaService(ownerRepository);
    }

    @Test
    void findByLastName() {
        //given
        String lastName = "lastName";
        Owner owner = Owner.builder().id(1L).lastName(lastName).build();

        //when
        BDDMockito.when(ownerRepository.findByLastName(owner.getLastName())).thenReturn(Optional.of(owner));
        Owner foundOwner = ownerService.findByLastName(owner.getLastName());

        //then
        Assertions.assertNotNull(foundOwner);
        Assertions.assertEquals(lastName, foundOwner.getLastName());
        BDDMockito.then(ownerRepository).should(BDDMockito.times(1)).findByLastName(owner.getLastName());
    }

    @Test
    void findByLastNameNotExist() {
        //given
        String lastName = "lastName";

        //when
        BDDMockito.when(ownerRepository.findByLastName(ArgumentMatchers.anyString())).thenReturn(Optional.empty());

        //then
        Assertions.assertNull(ownerService.findByLastName(lastName));
        BDDMockito.then(ownerRepository).should(BDDMockito.times(1)).findByLastName(lastName);
    }

    @Test
    void findById() {
        //given
        Long id = 1L;
        String lastName = "lastName";
        Owner owner = Owner.builder().id(id).lastName(lastName).build();

        //when
        BDDMockito.when(ownerRepository.findById(owner.getId())).thenReturn(Optional.of(owner));

        //then
        Assertions.assertEquals(owner, ownerService.findById(owner.getId()));
        BDDMockito.then(ownerRepository).should().findById(owner.getId());
    }

    @Test
    void findByIdNotExist() {
        //given
        Long id = 1L;

        //when
        BDDMockito.when(ownerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        //then
        Assertions.assertNull(ownerService.findById(id));
        BDDMockito.then(ownerRepository).should().findById(id);
    }

    @Test
    void findAll() {
        //given
        Owner owner1 = Owner.builder().id(1L).lastName("first lastName").build();
        Owner owner2 = Owner.builder().id(2L).lastName("first lastName").build();
        List<Owner> owners = Arrays.asList(owner1, owner2);

        //when
        BDDMockito.when(ownerRepository.findAll()).thenReturn(owners);

        //then
        Assertions.assertEquals(owners.size(), ownerService.findAll().size());
        BDDMockito.then(ownerRepository).should().findAll();

    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).lastName("first lastName").build();

        //when
        BDDMockito.when(ownerRepository.save(ownerToSave)).thenReturn(ownerToSave);

        //then
        Owner savedOwner = ownerService.save(ownerToSave);
        Assertions.assertEquals(ownerToSave, savedOwner);
        BDDMockito.then(ownerRepository).should().save(ArgumentMatchers.any(Owner.class));

    }

    @Test
    void delete() {
        Owner owner = Owner.builder().id(1L).lastName("first lastName").build();

        ownerService.delete(owner);

        BDDMockito.then(ownerRepository).should().delete(owner);
    }

    @Test
    void deleteById() {
        Owner owner = Owner.builder().id(1L).lastName("first lastName").build();

        ownerService.deleteById(owner.getId());

        BDDMockito.then(ownerRepository).should().deleteById(owner.getId());
    }

    @Test
    void findAllByLastNameLikeTest() {
        String lastName = "John";
        Owner owner1 = Owner.builder().id(1L).lastName(lastName).build();
        Owner owner2 = Owner.builder().id(2L).lastName(lastName).build();
        List<Owner> ownerList = Arrays.asList(owner1, owner2);

        BDDMockito.when(ownerRepository.findAllByLastNameLike(lastName)).thenReturn(ownerList);

        org.assertj.core.api.Assertions.assertThat(ownerService.findAllByLastNameLike(lastName)).isSameAs(ownerList);
    }

    @Test
    void findAllByLastNameLikeEmptyLastName() {
        Assertions.assertEquals(ownerService.findAll().size(), ownerService.findAllByLastNameLike("").size());
    }

}