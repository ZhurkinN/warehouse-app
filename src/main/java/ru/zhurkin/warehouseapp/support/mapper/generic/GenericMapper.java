package ru.zhurkin.warehouseapp.support.mapper.generic;

import ru.zhurkin.warehouseapp.model.generic.GenericModel;
import ru.zhurkin.warehouseapp.support.dto.generic.GenericDTO;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericMapper<E extends GenericModel, D extends GenericDTO> {

    public abstract E toEntity(D dto);

    public List<E> toEntities(List<D> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public abstract D toDto(E entity);

    public List<D> toDtos(List<E> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
