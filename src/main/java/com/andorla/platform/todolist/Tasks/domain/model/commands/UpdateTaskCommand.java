package com.andorla.platform.todolist.Tasks.domain.model.commands;

import com.andorla.platform.todolist.Tasks.domain.model.valueobjects.PriorityType;
import com.andorla.platform.todolist.Tasks.domain.model.valueobjects.StatusType;

public record UpdateTaskCommand(Long Id, String title, String description, PriorityType priority, StatusType status) {
}
