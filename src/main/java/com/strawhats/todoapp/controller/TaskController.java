package com.strawhats.todoapp.controller;


import com.strawhats.todoapp.config.AppConstants;
import com.strawhats.todoapp.payload.TaskDto;
import com.strawhats.todoapp.payload.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto createdTaskDto = taskService.createTask(taskDto);
        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    public ResponseEntity<TaskResponse> getAllTasks(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_ORDER) String sortOrder
    ) {
        TaskResponse taskResponse = taskService.getAllTasks(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long taskId) {
        TaskDto retrievedTaskDto = taskService.getTaskById(taskId);
        return new ResponseEntity<>(retrievedTaskDto, HttpStatus.OK);
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        TaskDto updatedTaskDto = taskService.updateTask(taskId, taskDto);
        return new ResponseEntity<>(updatedTaskDto, HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable Long taskId) {
        TaskDto deletedTaskDto = taskService.deleteTask(taskId);
        return new ResponseEntity<>(deletedTaskDto, HttpStatus.OK);
    }

    @PatchMapping("/tasks/{taskId}/complete")
    public ResponseEntity<TaskDto> markTaskCompleted(@PathVariable Long taskId) {
        TaskDto completedTaskDto = taskService.markTaskCompleted(taskId);
        return new ResponseEntity<>(completedTaskDto, HttpStatus.OK);
    }

}
