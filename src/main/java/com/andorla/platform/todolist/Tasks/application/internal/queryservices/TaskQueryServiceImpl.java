package com.andorla.platform.todolist.Tasks.application.internal.queryservices;

import com.andorla.platform.todolist.Tasks.domain.model.aggregates.Task;
import com.andorla.platform.todolist.Tasks.domain.model.queries.GetAllTasksQuery;
import com.andorla.platform.todolist.Tasks.domain.model.queries.GetTaskByIdQuery;
import com.andorla.platform.todolist.Tasks.domain.services.TaskQueryService;
import com.andorla.platform.todolist.Tasks.infrastructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskQueryServiceImpl implements TaskQueryService {

    private final TaskRepository taskRepository;

    public TaskQueryServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> handle(GetAllTasksQuery query) {
        return this.taskRepository.findAll();
    }

    @Override
    public Optional<Task> handle(GetTaskByIdQuery query) {
        return this.taskRepository.findById(query.Id());
    }
}
