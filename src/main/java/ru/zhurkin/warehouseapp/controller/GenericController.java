package ru.zhurkin.warehouseapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.warehouseapp.model.generic.GenericModel;
import ru.zhurkin.warehouseapp.service.GenericService;
import ru.zhurkin.warehouseapp.support.dto.generic.GenericDTO;
import ru.zhurkin.warehouseapp.support.mapper.generic.GenericMapper;

import java.util.List;

import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.RECORD_WAS_DELETED;

@RequiredArgsConstructor
public abstract class GenericController<D extends GenericDTO, M extends GenericModel> {

    private final GenericService<M> genericService;
    private final GenericMapper<M, D> genericMapper;

    @PostMapping
    @Operation(method = "add", description = "Add new record")
    public ResponseEntity<D> add(@RequestBody D dto) {

        M entity = genericMapper.toEntity(dto);
        M addedEntity = genericService.add(entity);
        return ResponseEntity.ok(genericMapper.toDto(addedEntity));
    }

    @GetMapping("/{id}")
    @Operation(method = "getById", description = "Find record by id")
    public ResponseEntity<D> getById(@PathVariable Long id) {

        M entity = genericService.getById(id);
        return ResponseEntity.ok(genericMapper.toDto(entity));
    }

    @GetMapping
    @Operation(method = "getAll", description = "Get all records")
    public ResponseEntity<List<D>> getAll() {

        List<M> entities = genericService.getAll();
        return ResponseEntity.ok(genericMapper.toDtos(entities));
    }

    @PutMapping
    @Operation(method = "edit", description = "Edit record")
    public ResponseEntity<D> edit(@RequestBody D dto) {

        M entity = genericMapper.toEntity(dto);
        M changedEntity = genericService.update(entity);
        return ResponseEntity.ok(genericMapper.toDto(changedEntity));
    }

    @DeleteMapping("/{id}")
    @Operation(method = "delete", description = "Delete record by id")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        genericService.delete(id);
        return ResponseEntity.ok(RECORD_WAS_DELETED);
    }
}
