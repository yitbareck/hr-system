package net.manyahl.departmentservice.mapper;

import net.manyahl.departmentservice.dto.DepartmentDto;
import net.manyahl.departmentservice.model.Department;

public class DepartmentMapper {
    public static DepartmentDto toDepartmentDto(Department department){
        return new DepartmentDto(
                department.getId(),
                department.getCode(),
                department.getName()
        );
    }

    public static Department toDepartment(DepartmentDto departmentDto){
        return new Department(
                departmentDto.getId(),
                departmentDto.getCode(),
                departmentDto.getName()
        );
    }
}
