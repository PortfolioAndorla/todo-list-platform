package com.andorla.platform.todolist.Tasks.interfaces.rest.transform;

import com.andorla.platform.todolist.Tasks.domain.model.commands.UpdateTaskCommand;
import com.andorla.platform.todolist.Tasks.interfaces.rest.resources.TaskResource;

public class UpdateTaskCommandFromResourceAssembler {
 public static UpdateTaskCommand toCommandFromResource(Long Id, TaskResource resource){
        return new UpdateTaskCommand(Id, resource.title(), resource.description(), resource.priority(), resource.status());
 }
}
