package com.andorla.platform.todolist.Tasks.domain.model.commands;

public record CreateTaskCommand (String title, String description, String priority, String status) {
}
