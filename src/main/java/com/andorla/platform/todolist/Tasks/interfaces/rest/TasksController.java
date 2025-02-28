package com.andorla.platform.todolist.Tasks.interfaces.rest;

import com.andorla.platform.todolist.Tasks.domain.model.commands.DeleteTaskCommand;
import com.andorla.platform.todolist.Tasks.domain.model.queries.GetAllTasksQuery;
import com.andorla.platform.todolist.Tasks.domain.model.queries.GetTaskByIdQuery;
import com.andorla.platform.todolist.Tasks.domain.services.TaskCommandService;
import com.andorla.platform.todolist.Tasks.domain.services.TaskQueryService;
import com.andorla.platform.todolist.Tasks.interfaces.rest.resources.CreateTaskResource;
import com.andorla.platform.todolist.Tasks.interfaces.rest.resources.TaskResource;
import com.andorla.platform.todolist.Tasks.interfaces.rest.transform.CreateTaskCommandFromResourceAssembler;
import com.andorla.platform.todolist.Tasks.interfaces.rest.transform.TaskResourceFromEntityAssembler;
import com.andorla.platform.todolist.Tasks.interfaces.rest.transform.UpdateTaskCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Tasks", description = "Tasks Management Endpoints")
public class TasksController {
    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;

    public TasksController(TaskCommandService taskCommandService, TaskQueryService taskQueryService) {
        this.taskCommandService = taskCommandService;
        this.taskQueryService = taskQueryService;
    }


    @PostMapping
    public ResponseEntity<TaskResource> createTask(@RequestBody CreateTaskResource resource) {
        var createTaskCommand = CreateTaskCommandFromResourceAssembler
                .toCommandFromResource(resource);
        var task = taskCommandService.handle(createTaskCommand);

        if (task.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var optionalTask = taskQueryService.handle(new GetTaskByIdQuery(task));
        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(optionalTask.get());
        return new ResponseEntity<>(taskResource, HttpStatus.CREATED);
    }


    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResource> getTaskById(@PathVariable Long taskId) {
        var getTaskByIdQuery = new GetTaskByIdQuery(taskId);
        var optionalTask = this.taskQueryService.handle(getTaskByIdQuery);
        if (optionalTask.isEmpty())
            return ResponseEntity.badRequest().build();
        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(optionalTask.get());
        return ResponseEntity.ok(taskResource);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResource> updateTask(@PathVariable Long taskId, @RequestBody TaskResource resource) {
        var updateTaskCommand = UpdateTaskCommandFromResourceAssembler.toCommandFromResource(taskId, resource);
        var optionalTask = this.taskCommandService.handle(updateTaskCommand);

        if (optionalTask.isEmpty())
            return ResponseEntity.badRequest().build();
        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(optionalTask.get());
        return ResponseEntity.ok(taskResource);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        var deleteTaskCommand = new DeleteTaskCommand(taskId);
        this.taskCommandService.handle(deleteTaskCommand);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TaskResource>> getAllReservations() {
        var getAllTasksQuery = new GetAllTasksQuery();
        var tasks = taskQueryService.handle(getAllTasksQuery);

        var reservationsResources = tasks.stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(reservationsResources);

    }

}
