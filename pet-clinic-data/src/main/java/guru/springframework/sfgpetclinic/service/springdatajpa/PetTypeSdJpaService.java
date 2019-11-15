package guru.springframework.sfgpetclinic.service.springdatajpa;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repository.PetTypeRepository;
import guru.springframework.sfgpetclinic.service.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Service
@Profile("springdatajpa")
@RequiredArgsConstructor
public class PetTypeSdJpaService implements PetTypeService {
    private final PetTypeRepository petTypeRepository;

    @Override
    public PetType findById(Long id) {
        return petTypeRepository.findById(id)
            .orElse(null);
    }

    @Override
    public Set<PetType> findAll() {
        return new HashSet<>(petTypeRepository.findAll());
    }

    @Override
    public PetType save(PetType petType) {
        return petTypeRepository.save(petType);
    }

    @Override
    public void delete(PetType petType) {
        petTypeRepository.delete(petType);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }

}
