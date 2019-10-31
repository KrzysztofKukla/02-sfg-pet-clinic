package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Krzysztof Kukla
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

    //box type is recommended by Hibernate
    private Long id;

}
