package ru.zhurkin.warehouseapp.controller.generic;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.warehouseapp.model.generic.GenericModel;
import ru.zhurkin.warehouseapp.service.generic.GenericService;
import ru.zhurkin.warehouseapp.support.dto.generic.GenericDTO;
import ru.zhurkin.warehouseapp.support.mapper.generic.GenericMapper;

import java.util.List;

import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.*;

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

    @PostMapping("/soft-delete/{id}")
    @Operation(method = "softDelete", description = "Soft delete record by id")
    public ResponseEntity<String> softDelete(@PathVariable Long id) {

        String resultMessage = genericService.softDelete(id)
                ? RECORD_WAS_DELETED
                : RECORD_WAS_NOT_DELETED;
        return ResponseEntity.ok(resultMessage);
    }

    @PostMapping("/restore/{id}")
    @Operation(method = "restore", description = "Restore deleted record by id")
    public ResponseEntity<String> restore(@PathVariable Long id) {

        genericService.restore(id);
        return ResponseEntity.ok(RECORD_WAS_RESTORED);
    }

}
