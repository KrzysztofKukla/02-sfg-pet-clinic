package guru.springframework.sfgpetclinic.service.map;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.service.SpecialityService;
import org.springframework.stereotype.Service;

/**
 * @author Krzysztof Kukla
 */
@Service
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialityService {
}
