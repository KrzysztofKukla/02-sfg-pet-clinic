package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Krzysztof Kukla
 */
@Entity
@Table(name = "speciality")
@Getter
@Setter
@ToString
public class Speciality extends BaseEntity {
    @Column(name = "description")
    private String description;

}
