package guru.springframework.sfgpetclinic.service.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repository.SpecialityRepository;
import guru.springframework.sfgpetclinic.service.SpecialityService;
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
@Profile("springdatajpa")
@RequiredArgsConstructor
public class SpecialitySdJpaService implements SpecialityService {
    private final SpecialityRepository specialityRepository;

    @Override
    public Speciality findById(Long id) {
        return specialityRepository.findById(id)
            .orElse(null);
    }

    @Override
    public Set<Speciality> findAll() {
        return Collections.unmodifiableSet(
            new HashSet<>(specialityRepository.findAll())
        );
    }

    @Override
    public Speciality save(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    @Override
    public void delete(Speciality speciality) {
        specialityRepository.delete(speciality);
    }

    @Override
    public void deleteById(Long id) {
        specialityRepository.deleteById(id);
    }

}
