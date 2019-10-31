package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Krzysztof Kukla
 */
@Getter
@Setter
public class Person extends BaseEntity {
    private String firstName;
    private String lastName;
}
