package com.strawhats.todoapp.service;

import com.strawhats.todoapp.payload.TaskDto;
import com.strawhats.todoapp.payload.TaskResponse;

public interface TaskService {

    TaskDto createTask(TaskDto taskDto);

    TaskResponse getAllTasks(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    TaskDto getTaskById(Long taskId);

    TaskDto updateTask(Long taskId, TaskDto taskDto);

    TaskDto deleteTask(Long taskId);

    TaskDto markTaskCompleted(Long taskId);

}
