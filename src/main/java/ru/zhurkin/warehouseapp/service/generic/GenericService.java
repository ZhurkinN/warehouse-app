package ru.zhurkin.warehouseapp.service.generic;

import ru.zhurkin.warehouseapp.model.generic.GenericModel;

import java.util.List;

public abstract class GenericService<T extends GenericModel> {

    public abstract T add(T entity);

    public abstract T getById(Long id);

    public abstract List<T> getAll();

    public abstract T update(T entity);

    public abstract void delete(Long id);
}
