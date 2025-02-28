package com.andorla.platform.todolist.Tasks.domain.model.aggregates;


import com.andorla.platform.todolist.Tasks.domain.model.commands.CreateTaskCommand;
import com.andorla.platform.todolist.Tasks.domain.model.valueobjects.PriorityType;
import com.andorla.platform.todolist.Tasks.domain.model.valueobjects.StatusType;
import com.andorla.platform.todolist.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Tasks")
public class Task extends AuditableAbstractAggregateRoot<Task> {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    @Enumerated(EnumType.STRING)
    private PriorityType priorityType;

    public Task(String title, String description, StatusType statusType, PriorityType priorityType) {
        this.title = title;
        this.description = description;
        this.statusType = statusType;
        this.priorityType = priorityType;
    }

    public Task(CreateTaskCommand command){
        this.title = command.title();
        this.description = command.description();
        this.statusType = StatusType.valueOf(command.status());
        this.priorityType = PriorityType.valueOf(command.priority());
    }

    public Task updateInformation(String title, String description, StatusType statusType, PriorityType priorityType) {
        this.title = title;
        this.description = description;
        this.statusType = StatusType.valueOf(String.valueOf(statusType)); // Asignar directamente
        this.priorityType = PriorityType.valueOf(String.valueOf(priorityType)); // Asignar directamente
        return this;
    }
    public Task() {
    }
}
