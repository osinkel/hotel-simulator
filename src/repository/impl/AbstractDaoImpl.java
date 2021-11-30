package repository.impl;

import repository.GenericDao;

import java.util.ArrayList;

public abstract class AbstractDaoImpl<T> implements GenericDao<T> {

    @Override
    public abstract boolean add(T entity);

}
