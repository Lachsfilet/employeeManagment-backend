package de.fynnberger.backend.employee;

import de.fynnberger.backend.todo.Todo;
import de.fynnberger.backend.todo.TodoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    public Employee(EmployeeDTO employeeDTO) {
        this.firstName = employeeDTO.getFirstName();
        this.lastName = employeeDTO.getLastName();
        this.email = employeeDTO.getEmail();
        this.image = employeeDTO.getImage();
    }
}