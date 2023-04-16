package ru.zhurkin.warehouseapp.support.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;
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
}
