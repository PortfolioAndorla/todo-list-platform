package com.andorla.platform.todolist.Tasks.application.internal.commandservices;

import com.andorla.platform.todolist.Tasks.domain.model.aggregates.Task;
import com.andorla.platform.todolist.Tasks.domain.model.commands.CreateTaskCommand;
import com.andorla.platform.todolist.Tasks.domain.model.commands.DeleteTaskCommand;
import com.andorla.platform.todolist.Tasks.domain.model.commands.UpdateTaskCommand;
import com.andorla.platform.todolist.Tasks.domain.services.TaskCommandService;
import com.andorla.platform.todolist.Tasks.infrastructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskCommandServiceImpl implements TaskCommandService {

    private final TaskRepository taskRepository;


    public TaskCommandServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Long handle(CreateTaskCommand command) {
        var task = new Task(command);
        try {
            this.taskRepository.save(task);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving profile: " + e.getMessage());
        }
        return task.getId();
    }

    @Override
    public Optional<Task> handle(UpdateTaskCommand command) {
        var taskId = command.Id();

        if (!this.taskRepository.existsById(taskId)) {
            throw new IllegalArgumentException("Task with id " + taskId + " does not exist");
        }

        var taskToUpdate = this.taskRepository.findById(taskId).get();
        taskToUpdate.updateInformation(command.title(), command.description(), command.status(), command.priority());

        try {
            var updatedProfile = this.taskRepository.save(taskToUpdate);
            return Optional.of(updatedProfile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating profile: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteTaskCommand command) {
        if (!this.taskRepository.existsById(command.Id())) {
            throw new IllegalArgumentException("Task with id " + command.Id() + " does not exist");
        }

        try {
            this.taskRepository.deleteById(command.Id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting profile: " + e.getMessage());
        }
    }

}
