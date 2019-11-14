package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import java.time.LocalDate;

/**
 * @author Krzysztof Kukla
 */
@Entity
@Getter
@Setter
public class Visit extends BaseEntity {

    private LocalDate date;
    private String description;

    @ManyToMany
    @JoinColumn(name = "pet_id")
    private Pet pet;

}
