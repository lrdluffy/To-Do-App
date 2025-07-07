package com.strawhats.todoapp.controller;


import com.strawhats.todoapp.config.AppConstants;
import com.strawhats.todoapp.payload.TaskDto;
import com.strawhats.todoapp.payload.TaskResponse;
import com.strawhats.todoapp.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "To-Do List API", description = "APIs & Rest Endpoints of To-Do Application")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Sucess"),
                    @ApiResponse(responseCode = "400", description = "Task already exists")
            }
    )
    @Operation(summary = "Create Task", description = "Create a new task")
    @PostMapping("/task")
    public ResponseEntity<TaskDto> createTask(
            @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Task object to be created"
            )
            @RequestBody TaskDto taskDto) {
        TaskDto createdTaskDto = taskService.createTask(taskDto);
        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success")
            }
    )
    @Operation(summary = "Get All Tasks", description = "Retrieve list of tasks")
    @GetMapping("/tasks")
    public ResponseEntity<TaskResponse> getAllTasks(
            @Parameter(name = "Page Number", required = false)
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @Parameter(name = "Page Size", required = false)
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @Parameter(name = "Sort Tasks By", required = false)
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
            @Parameter(name = "Sort Order", required = false)
            @RequestParam(defaultValue = AppConstants.SORT_ORDER) String sortOrder
    ) {
        TaskResponse taskResponse = taskService.getAllTasks(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }


    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Task not found")
            }
    )
    @Operation(summary = "Get Task by ID", description = "Retrieve a single task by ID")
    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(
            @Parameter(name = "taskId", description = "Id of Task object to be retrieved", required = true)
            @PathVariable Long taskId) {
        TaskDto retrievedTaskDto = taskService.getTaskById(taskId);
        return new ResponseEntity<>(retrievedTaskDto, HttpStatus.OK);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Task not found")
            }
    )
    @Operation(summary = "Update Task", description = "Update an existing task")
    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDto> updateTask(
            @Parameter(name = "taskId", description = "Id of Task object to be updated", required = true)
            @PathVariable Long taskId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Task object to be replaced", required = true)
            @RequestBody TaskDto taskDto) {
        TaskDto updatedTaskDto = taskService.updateTask(taskId, taskDto);
        return new ResponseEntity<>(updatedTaskDto, HttpStatus.OK);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Task not found")
            }
    )
    @Operation(summary = "Delete Task", description = "Delete an existing task")
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDto> deleteTask(
            @Parameter(name = "taskId", description = "Id of Task object to be deleted", required = true)
            @PathVariable Long taskId) {
        TaskDto deletedTaskDto = taskService.deleteTask(taskId);
        return new ResponseEntity<>(deletedTaskDto, HttpStatus.OK);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Task not found")
            }
    )
    @Operation(summary = "Mark Task Complete", description = "Mark a task as completed")
    @PatchMapping("/tasks/{taskId}/complete")
    public ResponseEntity<TaskDto> markTaskCompleted(
            @Parameter(name = "taskId", description = "Id of Task to be marked as completed", required = true)
            @PathVariable Long taskId) {
        TaskDto completedTaskDto = taskService.markTaskCompleted(taskId);
        return new ResponseEntity<>(completedTaskDto, HttpStatus.OK);
    }

}
