package com.andorla.platform.todolist.Tasks.domain.model.commands;

import com.andorla.platform.todolist.Tasks.domain.model.aggregates.Task;

public record DeleteTaskCommand(Long Id) {

}
