package net.manyahl.employeeservice.service;

import lombok.AllArgsConstructor;
import net.manyahl.employeeservice.dto.DepartmentDto;
import net.manyahl.employeeservice.dto.EmployeeDepartmentDto;
import net.manyahl.employeeservice.dto.EmployeeDto;
import net.manyahl.employeeservice.exception.EmployeeEmailExistsException;
import net.manyahl.employeeservice.exception.EmployeeNotFoundException;
import net.manyahl.employeeservice.mapper.EmployeeMapper;
import net.manyahl.employeeservice.model.Employee;
import net.manyahl.employeeservice.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    //private RestTemplate restTemplate;
    //private WebClient webClient;
    private ApiClient apiClient;

    public List<EmployeeDto> findAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(EmployeeMapper::toEmployeeDto)
                .toList();
    }

    public EmployeeDepartmentDto findByEmail(String email) {
        Employee employee = employeeRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new EmployeeNotFoundException("Employee doesn't exist"));
//RestTemplate
        //        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
//                "http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();
        //WebClient
//        DepartmentDto departmentDto = webClient
//                .get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();
        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());
        return new EmployeeDepartmentDto(
                EmployeeMapper.toEmployeeDto(employee),
                departmentDto
        );
    }

    public EmployeeDto save(EmployeeDto employeeDto) {
        Employee employee = employeeRepository
                .findByEmail(employeeDto.getEmail())
                .orElse(null);

        if (employee == null) {
            employee = EmployeeMapper.toEmployee(employeeDto);
            Employee savedEmployee = employeeRepository.save(employee);
            return EmployeeMapper.toEmployeeDto(savedEmployee);
        }
        throw new EmployeeEmailExistsException("Email already exists");
    }
}
