package ra.api.categy.service;

import java.util.List;

public interface IService<T, E> {
    List<T> findAll();

    T findById(E id);

    T save(T t);

    void delete(E id);
}
