package net.manyahl.employeeservice.controller;

import lombok.AllArgsConstructor;
import net.manyahl.employeeservice.dto.EmployeeDepartmentDto;
import net.manyahl.employeeservice.dto.EmployeeDto;
import net.manyahl.employeeservice.exception.EmployeeEmailExistsException;
import net.manyahl.employeeservice.exception.EmployeeNotFoundException;
import net.manyahl.employeeservice.exception.ExceptionResponse;
import net.manyahl.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<EmployeeDepartmentDto> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(employeeService.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.save(employeeDto), HttpStatus.CREATED);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ExceptionResponse handleEmployeeNotFoundException(
            EmployeeNotFoundException employeeNotFoundException,
            WebRequest webRequest) {
        return new ExceptionResponse(
                        employeeNotFoundException.getMessage(),
                        webRequest.getDescription(false),
                        LocalDateTime.now()
                );
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmployeeEmailExistsException.class)
    public ExceptionResponse handleEmployeeEmailExistsException(
            EmployeeEmailExistsException employeeEmailExistsException,
            WebRequest webRequest) {
        return new ExceptionResponse(
                employeeEmailExistsException.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now()
        );
    }
}
