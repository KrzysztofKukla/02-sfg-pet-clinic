package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * @author Krzysztof Kukla
 */
@Entity
@Getter
@Setter
public class Visit extends BaseEntity {

    private LocalDate localDate;
    private String description;
    private Pet pe;

}
