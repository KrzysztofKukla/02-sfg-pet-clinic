package guru.springframework.sfgpetclinic.service.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repository.VisitRepository;
import guru.springframework.sfgpetclinic.service.VisitService;
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
public class VisitSdJpaService implements VisitService {
    private final VisitRepository visitRepository;

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Visit object does not exist"));
    }

    @Override
    public Set<Visit> findAll() {
        return Collections.unmodifiableSet(
            new HashSet<>(visitRepository.findAll())
        );
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }

}
