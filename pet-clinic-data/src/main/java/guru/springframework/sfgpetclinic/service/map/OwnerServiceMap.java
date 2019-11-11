package guru.springframework.sfgpetclinic.service.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    @Override
    public Owner findByLastName(String lastName) {
        Optional<Map.Entry<Long, Owner>> first = map.entrySet().stream()
            .filter(entry -> entry.getValue().getLastName().equals(lastName))
            .findFirst();
        if (!first.isPresent()) {
            throw new RuntimeException();
        }
        return first.get().getValue();

    }

//    @Override
//    public Owner save(Owner owner) {
//        return super.save(owner);
//    }

}
