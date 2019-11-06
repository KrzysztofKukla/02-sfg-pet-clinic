package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Krzysztof Kukla
 */
@Getter
@Setter
@ToString
public class Person extends BaseEntity {
    private String firstName;
    private String lastName;
}
