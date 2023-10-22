package net.manyahl.employeeservice.exception;


public class EmployeeEmailExistsException extends RuntimeException{
    public EmployeeEmailExistsException(String message){
        super(message);
    }
}
