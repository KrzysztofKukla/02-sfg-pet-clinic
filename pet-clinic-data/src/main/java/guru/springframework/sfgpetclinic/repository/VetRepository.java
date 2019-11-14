package guru.springframework.sfgpetclinic.repository;

import guru.springframework.sfgpetclinic.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Krzysztof Kukla
 */
public interface VetRepository extends JpaRepository<Vet, Long> {
}
