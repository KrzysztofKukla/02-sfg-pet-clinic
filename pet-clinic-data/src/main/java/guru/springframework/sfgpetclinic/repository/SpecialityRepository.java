package guru.springframework.sfgpetclinic.repository;

import guru.springframework.sfgpetclinic.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Krzysztof Kukla
 */
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
}
