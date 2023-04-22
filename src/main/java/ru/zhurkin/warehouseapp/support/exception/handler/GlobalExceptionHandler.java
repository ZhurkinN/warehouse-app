package ru.zhurkin.warehouseapp.support.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.support.exception.IllegalRequestParameterException;
import ru.zhurkin.warehouseapp.support.exception.RolePermissionsException;
import ru.zhurkin.warehouseapp.support.exception.UserAlreadyExistsException;
import ru.zhurkin.warehouseapp.support.exception.model.ErrorDTO;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException e,
                                                            HttpServletRequest request) {
        ErrorDTO dto = new ErrorDTO(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleUserAlreadyExistsException(UserAlreadyExistsException e,
                                                                     HttpServletRequest request) {
        ErrorDTO dto = new ErrorDTO(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RolePermissionsException.class)
    public ResponseEntity<ErrorDTO> handleRolePermissionsException(RolePermissionsException e,
                                                                   HttpServletRequest request) {
        ErrorDTO dto = new ErrorDTO(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(dto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalRequestParameterException.class)
    public ResponseEntity<ErrorDTO> handleIllegalRequestParameterException(IllegalRequestParameterException e,
                                                                           HttpServletRequest request) {
        ErrorDTO dto = new ErrorDTO(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }
}
