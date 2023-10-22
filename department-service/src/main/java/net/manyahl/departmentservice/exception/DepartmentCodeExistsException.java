package net.manyahl.departmentservice.exception;


public class DepartmentCodeExistsException extends RuntimeException{
    public DepartmentCodeExistsException(String message){
        super(message);
    }
}
