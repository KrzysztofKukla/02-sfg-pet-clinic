package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Krzysztof Kukla
 */
//this indicates this object will not be in database
@MappedSuperclass
@Getter
@Setter
@ToString
public class Person extends BaseEntity {

    // Hibernate by default uses snake case, I mean "first_name"
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
