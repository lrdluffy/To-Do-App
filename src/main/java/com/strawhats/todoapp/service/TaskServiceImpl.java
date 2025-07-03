package com.strawhats.todoapp.service;

import com.strawhats.todoapp.exceptions.ApiException;
import com.strawhats.todoapp.exceptions.ResourceNotFoundException;
import com.strawhats.todoapp.model.Task;
import com.strawhats.todoapp.payload.TaskDto;
import com.strawhats.todoapp.payload.TaskResponse;
import com.strawhats.todoapp.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        String title = taskDto.getTitle();
        LocalDate dueDate = taskDto.getDueDate();
        Optional<Task> taskOptional = taskRepository.findByTitleAndDueDate(title, dueDate);
        if (taskOptional.isPresent()) {
            throw new ApiException("Task with title : " + title + " & dueDate : " + dueDate.toString() + " already exists");
        }

        Task task = modelMapper.map(taskDto, Task.class);
        taskRepository.save(task);
        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public TaskResponse getAllTasks(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Task> taskPage = taskRepository.findAll(pageable);
        List<TaskDto> content = taskPage.getContent().stream()
                .map(task -> modelMapper.map(task, TaskDto.class)).toList();

        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setContent(content);
        taskResponse.setPageNumber(taskPage.getNumber());
        taskResponse.setPageSize(taskPage.getSize());
        taskResponse.setTotalPages(taskResponse.getTotalPages() == null ? 0 : taskResponse.getTotalPages());
        taskResponse.setTotalElements(taskPage.getTotalElements());
        taskResponse.setLastPage(taskPage.isLast());

        return taskResponse;
    }

    @Override
    public TaskDto getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId));

        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto taskDto) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId));

        task = modelMapper.map(taskDto, Task.class);
        task.setId(taskId);
        taskRepository.save(task);

        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public TaskDto deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId));

        taskRepository.delete(task);
        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public TaskDto markTaskCompleted(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId));

        task.setCompleted(true);
        Task updatedTask = taskRepository.save(task);
        return modelMapper.map(updatedTask, TaskDto.class);
    }
}
