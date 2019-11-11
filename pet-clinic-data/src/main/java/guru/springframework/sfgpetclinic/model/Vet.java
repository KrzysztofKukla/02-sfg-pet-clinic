package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
//@Entity
@Getter
@Setter
public class Vet extends Person {
    private Set<Specialty> specialties;
}
