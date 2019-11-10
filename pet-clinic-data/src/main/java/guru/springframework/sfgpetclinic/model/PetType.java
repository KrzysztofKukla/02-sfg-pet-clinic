package guru.springframework.sfgpetclinic.model;

import javax.persistence.Entity;

/**
 * @author Krzysztof Kukla
 */
@Entity
public class PetType extends BaseEntity {
    private String name;
}
