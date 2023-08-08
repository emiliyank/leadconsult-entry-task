package LeadConsult.Lead.Consult.s.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {
    private static final String DATE_TIME_FORMAT_PATTERN = "HH:mm 'on' dd/MM/yyyy";
    private static final String TIMESTAMP_KEY = "timestamp";
    private static final String ERROR_KEY = "error";

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e){
        return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object>handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, List<String>> fieldErrorsMap = getFieldErrors(e);

        return createErrorResponse(HttpStatus.BAD_REQUEST, fieldErrorsMap);
    }

    private static Map<String, List<String>> getFieldErrors(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        Map<String, List<String>> fieldErrorsMap = new HashMap<>();
        for (FieldError error : fieldErrors) {
            String fieldName = error.getField();
            String errorMessage =
                    error.getDefaultMessage() != null
                            ? error.getDefaultMessage()
                            : "Error message not available";

            if (!fieldErrorsMap.containsKey(fieldName)) {
                fieldErrorsMap.put(fieldName, new ArrayList<>());
            }

            fieldErrorsMap.get(fieldName).add(errorMessage);
        }
        return fieldErrorsMap;
    }

    private ResponseEntity<Object> createErrorResponse(HttpStatus status, Object error) {
        Map<String, Object> body = new HashMap<>();
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN));
        body.put(TIMESTAMP_KEY, date);
        body.put(ERROR_KEY, error);

        return new ResponseEntity<>(body, status);
    }
}
