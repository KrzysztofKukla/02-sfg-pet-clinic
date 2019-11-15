package guru.springframework.sfgpetclinic.service.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repository.PetRepository;
import guru.springframework.sfgpetclinic.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Service
@Profile(value = "springdatajpa")
@RequiredArgsConstructor
public class PetSdJpaService implements PetService {
    private final PetRepository petRepository;

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id)
            .orElse(null);
    }

    @Override
    public Set<Pet> findAll() {
        return Collections.unmodifiableSet(new HashSet<>(petRepository.findAll()));
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void delete(Pet pet) {
        petRepository.delete(pet);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }

}
