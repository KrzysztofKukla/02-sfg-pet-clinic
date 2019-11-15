package guru.springframework.sfgpetclinic.service.springdatajpa;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repository.VetRepository;
import guru.springframework.sfgpetclinic.service.VetService;
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
public class VetSdJpaService implements VetService {
    private final VetRepository vetRepository;

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id)
            .orElse(null);
    }

    @Override
    public Set<Vet> findAll() {
        return new HashSet<>(vetRepository.findAll());
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        vetRepository.save(vet);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }

}
