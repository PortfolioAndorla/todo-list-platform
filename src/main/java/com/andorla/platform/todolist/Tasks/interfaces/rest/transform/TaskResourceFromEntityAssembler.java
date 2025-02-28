package com.andorla.platform.todolist.Tasks.interfaces.rest.transform;

import com.andorla.platform.todolist.Tasks.domain.model.aggregates.Task;
import com.andorla.platform.todolist.Tasks.interfaces.rest.resources.TaskResource;

public class TaskResourceFromEntityAssembler {
    public static TaskResource toResourceFromEntity(Task task) {
        return new TaskResource(task.getId(), task.getTitle(), task.getDescription(), task.getPriorityType(), task.getStatusType());
    }
}
