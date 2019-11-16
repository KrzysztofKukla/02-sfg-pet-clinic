package guru.springframework.sfgpetclinic.service.map;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.service.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author Krzysztof Kukla
 */
@Service
@Profile({"default", "map"})
@Slf4j
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {
}
