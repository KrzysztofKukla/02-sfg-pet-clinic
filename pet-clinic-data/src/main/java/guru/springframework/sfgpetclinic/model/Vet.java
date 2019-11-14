package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Entity
@Table(name = "vet")
@Getter
@Setter
@ToString
public class Vet extends Person {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties",
        joinColumns = @JoinColumn(name = "vet_id"),
        inverseJoinColumns = @JoinColumn(name = "speciality_id")
    )
    private Set<Speciality> specialties = new HashSet<>();
}
