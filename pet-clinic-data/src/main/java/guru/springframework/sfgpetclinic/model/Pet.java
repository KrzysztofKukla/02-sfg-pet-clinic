package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Krzysztof Kukla
 */
@Getter
@Setter
//@Entity
public class Pet extends BaseEntity {
    private String name;
    private PetType petType;
    private Owner owner;
    private LocalDate birthDate;
}
