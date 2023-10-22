package net.manyahl.departmentservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private String message;
    private String path;
    private LocalDateTime dateTime;
}
