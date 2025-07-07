package com.strawhats.todoapp.payload;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "Task DTO entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    @Schema(description = "Task Id", example = "1", hidden = true)
    private Long id;
    @Schema(description = "Task Title", example = "Task title goes here")
    private String title;
    @Schema(description = "Task Description", example = "Task description goes here")
    private String description;
    @Schema(description = "Task Status", example = "true/false")
    private boolean completed;
    @Schema(description = "Task Due Date", example = "2025-07-06")
    private LocalDate dueDate;

}
