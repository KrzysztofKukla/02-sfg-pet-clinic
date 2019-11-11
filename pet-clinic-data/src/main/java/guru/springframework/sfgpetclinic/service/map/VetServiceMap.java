package guru.springframework.sfgpetclinic.service.map;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.service.VetService;
import org.springframework.stereotype.Service;

/**
 * @author Krzysztof Kukla
 */
@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
//    @Override
//    public Vet save(Vet vet) {
//        return super.save(vet);
//    }
}
