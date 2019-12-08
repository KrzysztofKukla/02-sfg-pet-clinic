package guru.springframework.sfgpetclinic.service.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.service.OwnerService;
import guru.springframework.sfgpetclinic.service.PetService;
import guru.springframework.sfgpetclinic.service.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
@Service
@Profile({"default", "map"})
@RequiredArgsConstructor
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    @Override
    public Owner findByLastName(String lastName) {
        Optional<Map.Entry<Long, Owner>> first = map.entrySet().stream()
            .filter(entry -> entry.getValue().getLastName().equals(lastName))
            .findFirst();
        if (!first.isPresent()) {
            throw new RuntimeException("Invalid lastName-> " + lastName);
        }
        return first.get().getValue();

    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        //TODO
        return Collections.emptyList();
    }

    //to demonstrate and mimic how Hibernate does that
    //here we're doing manually
    @Override
    public Owner save(Owner object) {
        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("PetType is required and cannot be null");
                    }
                    if (pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }
    }

}
