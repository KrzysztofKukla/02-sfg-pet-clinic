package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.BaseEntity;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.service.OwnerService;
import guru.springframework.sfgpetclinic.service.PetTypeService;
import guru.springframework.sfgpetclinic.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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

        PetType dogPetType = createPetType("dogPetType");
        Owner owner1 = createOwner("Michael", "Weston", "Kasztanowa 31", "Krakow", "12345");
        Pet mikesPet = createPet(dogPetType, owner1, LocalDate.now(), "Rosco");
        owner1.getPets().add(mikesPet);
        ownerService.save(owner1);

        PetType catPetType = createPetType("catPetType");
        Owner owner2 = createOwner("Fiona", "Glenanne", "Krakowska 56", "Warszawa", "78899");
        Pet fionaCat = createPet(catPetType, owner2, LocalDate.now(), "just cat");
        owner2.getPets().add(fionaCat);
        ownerService.save(owner2);
        System.out.println("List of owners: ");
        displayEnties(ownerService.findAll());

        createVet("Sam", "Axe");
        createVet("Jessie", "Porter");
        System.out.println("List of vets: ");
        displayEnties(vetService.findAll());

    }

    private Pet createPet(PetType petType, Owner owner, LocalDate birthDate, String name) {
        Pet pet = new Pet();
        pet.setPetType(petType);
        pet.setOwner(owner);
        pet.setBirthDate(birthDate);
        pet.setName(name);
        return pet;
    }

    private PetType createPetType(String name) {
        PetType petType = new PetType();
        petType.setName(name);
        return petTypeService.save(petType);
    }

    private void createVet(String firstName, String lastName) {
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

    private Owner createOwner(String firstName, String lastName, String address, String city, String telephone) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setTelephone(telephone);
        return owner;
    }

}
