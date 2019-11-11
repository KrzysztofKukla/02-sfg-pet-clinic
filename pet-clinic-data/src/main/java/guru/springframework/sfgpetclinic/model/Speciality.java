package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Krzysztof Kukla
 */
//@Entity
@Getter
@Setter
@ToString
public class Speciality extends BaseEntity {
    private String description;

}
