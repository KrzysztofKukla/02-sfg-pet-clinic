package guru.springframework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetType extends BaseEntity {
    private String name;

    @Override
    public String toString() {
        return name;
    }

}
