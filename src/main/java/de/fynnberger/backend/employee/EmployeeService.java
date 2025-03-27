package de.fynnberger.backend.employee;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import de.fynnberger.backend.employee.utils.ExcelHelper;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ExcelHelper excelHelper;

    public EmployeeService(EmployeeRepository employeeRepository, ExcelHelper excelHelper) {
        this.employeeRepository = employeeRepository;
        this.excelHelper = excelHelper;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        throw new EmployeeNotFoundException(id);
    }

    public void deleteEmployee(long id) throws EmployeeNotFoundException {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }

    public void createEmployee(EmployeeDTO givenEmployee) {
        Employee employee = new Employee(givenEmployee);
        employeeRepository.save(employee);
    }

    public void updateEmployee(long id, EmployeeDTO givenEmployee) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employee.setFirstName(givenEmployee.getFirstName());
        employee.setLastName(givenEmployee.getLastName());
        employee.setEmail(givenEmployee.getEmail());
        employee.setImage(givenEmployee.getImage());
        employeeRepository.save(employee);
    }

    public void uploadEmployees(MultipartFile file) throws Exception {
        List<EmployeeDTO> employeeDtos = this.excelHelper.parseExcelFile(file);
        List<Employee> employees = employeeDtos.stream().map(Employee::new).toList();

        employeeRepository.saveAll(employees);
    }

    public byte[] downloadEmployees() {
        List<Employee> employees = getEmployees();
        return this.excelHelper.writeEmployeesToExcel(employees);
    }
}