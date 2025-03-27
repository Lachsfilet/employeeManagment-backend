package de.fynnberger.backend.todo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class TodoDTO extends CreationTodoDTO {
    private long id;

    public TodoDTO(long id, String title, boolean completed, long employeeId) {
        super(title, completed, employeeId);
        this.id = id;
    }
}
