package net.manyahl.employeeservice.mapper;

import net.manyahl.employeeservice.dto.EmployeeDto;
import net.manyahl.employeeservice.model.Employee;

public class EmployeeMapper {
    public static Employee toEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
    }
    public static EmployeeDto toEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );
    }
}
