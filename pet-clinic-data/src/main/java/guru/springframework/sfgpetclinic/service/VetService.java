package guru.springframework.sfgpetclinic.service;

import guru.springframework.sfgpetclinic.model.Vet;

import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
public interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();

}
