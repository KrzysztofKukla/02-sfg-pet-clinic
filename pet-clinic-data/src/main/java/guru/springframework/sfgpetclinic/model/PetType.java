package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Krzysztof Kukla
 */
//@Entity
@Getter
@Setter
public class PetType extends BaseEntity {
    private String name;
}
