package ru.yandex.productmanager.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.yandex.productmanager.dto.Error;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {NoSuchElementException.class})
    protected ResponseEntity<Error> noFoundHandler(
            RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(404).body(new Error(404, "Item not found"));
    }


    @ExceptionHandler(value
            = {IllegalArgumentException.class})
    protected ResponseEntity<Error> validationHandler(
            RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(400).body(new Error(400, "Validation Failed"));
    }

}