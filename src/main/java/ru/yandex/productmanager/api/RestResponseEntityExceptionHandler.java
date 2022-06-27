package ru.yandex.productmanager.api;

import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.yandex.productmanager.dto.Error;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {NoSuchElementException.class})
    protected ResponseEntity<Error> noFoundHandler(
            RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(404).body(new Error(404, "Item not found"));
    }




    @ExceptionHandler(Exception.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex) {
        return new Error(400,"validation failed");
    }


}