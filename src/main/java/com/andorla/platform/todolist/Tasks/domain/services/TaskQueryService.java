package com.andorla.platform.todolist.Tasks.domain.services;

import com.andorla.platform.todolist.Tasks.domain.model.aggregates.Task;
import com.andorla.platform.todolist.Tasks.domain.model.queries.GetAllTasksQuery;
import com.andorla.platform.todolist.Tasks.domain.model.queries.GetTaskByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TaskQueryService {
    List<Task> handle(GetAllTasksQuery query);
    Optional<Task> handle(GetTaskByIdQuery query);
}
