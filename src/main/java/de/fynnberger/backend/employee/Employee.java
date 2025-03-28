package de.fynnberger.backend.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.fynnberger.backend.todo.Todo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Todo> todos;

    public Employee(EmployeeDTO employeeDTO) {
        this.firstName = employeeDTO.getFirstName();
        this.lastName = employeeDTO.getLastName();
        this.email = employeeDTO.getEmail();
        this.image = employeeDTO.getImage();
    }
}