package guru.springframework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Krzysztof Kukla
 */
//this indicates that object is not be created in database
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

    @Id
    //IDENTITY is a special type that will support the automatic generation of a sequence
    // for example MySql, not provided in each ones of databases
    // added in Oracle 12
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //box type is recommended by Hibernate
    private Long id;

    public boolean isNew() {
        return this.id == null;
    }

}
