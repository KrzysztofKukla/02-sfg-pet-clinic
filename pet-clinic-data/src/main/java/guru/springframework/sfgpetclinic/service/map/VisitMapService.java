package guru.springframework.sfgpetclinic.service.map;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.service.VisitService;
import org.springframework.stereotype.Service;

/**
 * @author Krzysztof Kukla
 */
@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Visit save(Visit visit) {
        if (visit.getPet() == null || visit.getPet().getId() == null || visit.getPet().getOwner() == null || visit.getPet().getOwner().getId() == null) {
            throw new RuntimeException("Invalid Visit");
        }
        return super.save(visit);
    }

}
