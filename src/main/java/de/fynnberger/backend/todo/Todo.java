package de.fynnberger.backend.todo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.fynnberger.backend.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean completed;
    @ManyToOne
    @JsonIgnore
    private Employee employee;

    public Todo(CreationTodoDTO creationTodoDTO, Employee employee) {
        this.title = creationTodoDTO.getTitle();
        this.completed = creationTodoDTO.isCompleted();
        this.employee = employee;
    }
}