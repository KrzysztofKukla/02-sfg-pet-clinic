package guru.springframework.sfgpetclinic.service.map;

import guru.springframework.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Krzysztof Kukla
 */
class PetMapServiceTest {

    private Map<Long, Pet> pets;

    private PetMapService petMapService;

    @BeforeEach
    void setUp() {

        Pet pet1 = Pet.builder().name("first").build();
        Pet pet2 = Pet.builder().name("second").build();
        Pet pet3 = Pet.builder().name("third").build();

        createPets(pet1, pet2, pet3);

        savePetMapService(pet1, pet2, pet3);
    }

    @Test
    void findAll() {
        Assertions.assertEquals(pets.size(), petMapService.findAll().size());
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    private void savePetMapService(Pet... pets) {
        petMapService = new PetMapService();
        Stream.of(pets).forEach(petMapService::save);
    }

    private void createPets(Pet pet1, Pet pet2, Pet pet3) {
        pets = new HashMap<>();
        pets.put(1L, pet1);
        pets.put(2L, pet2);
        pets.put(3L, pet3);
    }

}