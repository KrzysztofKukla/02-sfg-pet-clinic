package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Krzysztof Kukla
 */
@Getter
@Setter
public class Pet {
    private PetType petType;
    private Owner owner;
    private LocalDate birthDate;
}
