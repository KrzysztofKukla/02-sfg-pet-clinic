package guru.springframework.sfgpetclinic.service.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repository.OwnerRepository;
import guru.springframework.sfgpetclinic.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Service
@Profile(value = "springdatajpa")
@RequiredArgsConstructor
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public Owner findByLastName(String lastName) {
        Optional<Owner> ownerOptional = ownerRepository.findByLastName(lastName);
        return ownerOptional.orElse(null);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Owner findById(Long id) {
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        return ownerOptional.orElse(null);
    }

    @Override
    public Set<Owner> findAll() {
        return new HashSet<>(ownerRepository.findAll());
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

}
