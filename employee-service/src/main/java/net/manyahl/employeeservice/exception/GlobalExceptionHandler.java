package net.manyahl.employeeservice.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleException(Exception exception,
                                             WebRequest webRequest){
        return new ExceptionResponse(
                exception.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now());
    }
}
