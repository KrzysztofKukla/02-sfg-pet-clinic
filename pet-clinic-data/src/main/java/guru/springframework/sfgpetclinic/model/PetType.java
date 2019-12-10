package guru.springframework.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Krzysztof Kukla
 */
@Entity
@Table(name = "pet_type")
@Getter
@Setter
@NoArgsConstructor
public class PetType extends BaseEntity {
    private String name;

    //it allows to specify id field for PetType as well
    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
