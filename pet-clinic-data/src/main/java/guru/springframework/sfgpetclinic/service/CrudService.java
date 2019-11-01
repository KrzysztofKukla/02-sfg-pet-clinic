package guru.springframework.sfgpetclinic.service;

import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
public interface CrudService<T, ID> {

    T findById(ID id);

    Set<T> findAll();

    T save(T t);

    void delete(T t);

    void deleteById(ID id);

}
