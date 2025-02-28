package com.andorla.platform.todolist.Tasks.domain.services;

import com.andorla.platform.todolist.Tasks.domain.model.aggregates.Task;
import com.andorla.platform.todolist.Tasks.domain.model.commands.CreateTaskCommand;
import com.andorla.platform.todolist.Tasks.domain.model.commands.DeleteTaskCommand;
import com.andorla.platform.todolist.Tasks.domain.model.commands.UpdateTaskCommand;

import java.util.Optional;

public interface TaskCommandService {
    Long handle(CreateTaskCommand command);
    Optional<Task> handle(UpdateTaskCommand command);
    void handle(DeleteTaskCommand command);
}
