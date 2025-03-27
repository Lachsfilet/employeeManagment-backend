package de.fynnberger.backend.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreationTodoDTO {
    private String title;
    private boolean completed;
    private long employeeId;
}