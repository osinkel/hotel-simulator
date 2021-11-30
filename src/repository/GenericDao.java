package repository;

import java.util.ArrayList;

public interface GenericDao<T> {
    boolean add(T entity);
}
