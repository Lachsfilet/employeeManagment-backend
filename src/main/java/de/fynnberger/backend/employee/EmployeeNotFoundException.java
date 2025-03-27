package de.fynnberger.backend.employee;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(long id) {
        super("Employee with id " + id + " not found");
    }
}
