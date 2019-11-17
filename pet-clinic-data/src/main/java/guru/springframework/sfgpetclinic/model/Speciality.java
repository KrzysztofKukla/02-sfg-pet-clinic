package guru.springframework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Speciality extends BaseEntity {
    @Column(name = "description")
    private String description;

}
