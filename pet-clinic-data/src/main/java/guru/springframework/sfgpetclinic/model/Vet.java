package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
//@Entity
@Getter
@Setter
@ToString
public class Vet extends Person {
    private Set<Speciality> specialties = new HashSet<>();
}
