package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Entity
@Getter
@Setter
public class Owner extends Person {

    private String address;
    private String city;
    private String telephone;
    private Set<Pet> pets;
}
