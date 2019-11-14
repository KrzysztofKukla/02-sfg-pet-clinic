package guru.springframework.sfgpetclinic.repository;

import guru.springframework.sfgpetclinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Krzysztof Kukla
 */
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
