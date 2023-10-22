package net.manyahl.departmentservice.service;

import lombok.AllArgsConstructor;
import net.manyahl.departmentservice.dto.DepartmentDto;
import net.manyahl.departmentservice.exception.DepartmentCodeExistsException;
import net.manyahl.departmentservice.exception.DepartmentNotFoundException;
import net.manyahl.departmentservice.mapper.DepartmentMapper;
import net.manyahl.departmentservice.model.Department;
import net.manyahl.departmentservice.repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    public DepartmentDto findByCode(String code){
        Department department= departmentRepository
                .findByCode(code)
                .orElseThrow(
                        ()-> new DepartmentNotFoundException("Department doesn't exist"));
        return DepartmentMapper.toDepartmentDto(department);
    }
    public List<DepartmentDto> findAll(){
        return departmentRepository
                .findAll()
                .stream()
                .map(DepartmentMapper::toDepartmentDto)
                .toList();
    }
    public DepartmentDto save(DepartmentDto departmentDto){
        Department department = departmentRepository
                .findByCode(departmentDto.getCode())
                .orElse(null);
        if(department == null) {
            department = DepartmentMapper.toDepartment(departmentDto);
            Department savedDepartment = departmentRepository.save(department);
            return DepartmentMapper.toDepartmentDto(savedDepartment);
        }
        throw new DepartmentCodeExistsException("Department code already exists");
    }
}
