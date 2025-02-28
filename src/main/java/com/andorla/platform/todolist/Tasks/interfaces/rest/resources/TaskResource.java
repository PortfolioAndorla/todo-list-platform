package com.andorla.platform.todolist.Tasks.interfaces.rest.resources;

import com.andorla.platform.todolist.Tasks.domain.model.valueobjects.PriorityType;
import com.andorla.platform.todolist.Tasks.domain.model.valueobjects.StatusType;

public record TaskResource(Long id,String title, String description, PriorityType priority, StatusType status) {
}
