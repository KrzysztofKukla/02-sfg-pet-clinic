package guru.springframework.sfgpetclinic.repository;

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByLastName(String lastName);
}
