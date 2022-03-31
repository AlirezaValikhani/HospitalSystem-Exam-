package repository.base;

import java.util.List;

public interface GenericRepository<T,ID> {
    T add(T t);
    T update(T t);
    void delete(T t);
    T findById(ID id);
    List<T> findAll();
}
