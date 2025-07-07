package com.strawhats.todoapp.payload;

import com.strawhats.todoapp.config.AppConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Task Response entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    @Schema(description = "A List of Tasks")
    private List<TaskDto> content;
    @Schema(description = "Page Number", example = "1", defaultValue = AppConstants.PAGE_NUMBER)
    private int pageNumber;
    @Schema(description = "Page Size", example = "20", defaultValue = AppConstants.PAGE_SIZE)
    private int pageSize;
    @Schema(description = "Total Pages Number", example = "1000")
    private Long totalPages;
    @Schema(description = "Total Elements Number", example = "1000000")
    private Long totalElements;
    @Schema(description = "Is Last Page or Not", example = "true/false")
    private boolean isLastPage;

}
