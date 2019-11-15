package guru.springframework.sfgpetclinic.service.map;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.service.PetService;
import org.springframework.stereotype.Service;

/**
 * @author Krzysztof Kukla
 */
@Service
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {
//    @Override
//    public Pet save(Pet pet) {
//        return super.save(pet);
//    }

}
