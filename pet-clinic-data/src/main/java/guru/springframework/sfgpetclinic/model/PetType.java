package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Krzysztof Kukla
 */
@Entity
@Table(name = "pet_type")
@Getter
@Setter
public class PetType extends BaseEntity {
    @Column(name = "name")
    private String name;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "petType")
//    private Set<Pet> pets;
}
