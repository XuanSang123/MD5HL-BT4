package ra.api.categy.advice;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import ra.api.categy.dto.response.EmployeeError;
import ra.api.categy.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class APIControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        EmployeeError employeeError = new EmployeeError(404, e.getMessage()); // HTTP 404 for not found
        Map<String, Object> response = new HashMap<>();
        response.put("error", employeeError);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException e) {
        EmployeeError employeeError = new EmployeeError(404, e.getMessage()); // HTTP 404 for resource not found
        Map<String, Object> response = new HashMap<>();
        response.put("error", employeeError);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Return 404
    }

}
