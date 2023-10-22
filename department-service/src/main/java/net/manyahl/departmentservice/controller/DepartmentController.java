package net.manyahl.departmentservice.controller;

import lombok.AllArgsConstructor;
import net.manyahl.departmentservice.dto.DepartmentDto;
import net.manyahl.departmentservice.exception.DepartmentCodeExistsException;
import net.manyahl.departmentservice.exception.DepartmentNotFoundException;
import net.manyahl.departmentservice.exception.ExceptionResponse;
import net.manyahl.departmentservice.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> findAll() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/{department-code}")
    public ResponseEntity<DepartmentDto> findByCode(@PathVariable("department-code") String code) {
        return ResponseEntity.ok(departmentService.findByCode(code));
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> save(@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.save(departmentDto), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ExceptionResponse handleDepartmentNotFoundException(
            DepartmentNotFoundException departmentNotFoundException,
            WebRequest webRequest) {
        return new ExceptionResponse(
                        departmentNotFoundException.getMessage(),
                        webRequest.getDescription(false),
                        LocalDateTime.now()
                );
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DepartmentCodeExistsException.class)
    public ExceptionResponse handleDepartmentCodeExistsException(
            DepartmentCodeExistsException departmentCodeExistsException,
            WebRequest webRequest) {
        return new ExceptionResponse(
                departmentCodeExistsException.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now()
        );
    }
}
