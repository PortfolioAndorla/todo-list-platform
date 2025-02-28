package com.andorla.platform.todolist.Tasks.interfaces.rest.transform;

import com.andorla.platform.todolist.Tasks.domain.model.commands.CreateTaskCommand;
import com.andorla.platform.todolist.Tasks.interfaces.rest.resources.CreateTaskResource;

public class CreateTaskCommandFromResourceAssembler {
    public static CreateTaskCommand toCommandFromResource(CreateTaskResource resource) {
        return new CreateTaskCommand(resource.title(), resource.description(), resource.priority(), resource.status());
    }
}
