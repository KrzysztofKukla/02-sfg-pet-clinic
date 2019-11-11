package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.BaseEntity;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.service.OwnerService;
import guru.springframework.sfgpetclinic.service.PetTypeService;
import guru.springframework.sfgpetclinic.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Override
    public void run(String... args) throws Exception {

        PetType dog = createPetType("dog");
        PetType cat = createPetType("cat");

        populateOwners("Michael", "Weston");
        populateOwners("Fiona", "Glenanne");
        System.out.println("List of owners: ");
        displayEnties(ownerService.findAll());

        populateVets("Sam", "Axe");
        populateVets("Jessie", "Porter");
        System.out.println("List of vets: ");
        displayEnties(vetService.findAll());

    }

    private PetType createPetType(String name) {
        PetType petType = new PetType();
        petType.setName(name);
        return petTypeService.save(petType);
    }

    private void populateVets(String firstName, String lastName) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vetService.save(vet);
    }

    private void displayEnties(Set<? extends BaseEntity> entities) {
        for (BaseEntity entity : entities) {
            System.out.println(entity);
        }
    }

    private void populateOwners(String firstName, String lastName) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        ownerService.save(owner);
    }

}
