package com.strawhats.todoapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    private List<TaskDto> content;
    private int pageNumber;
    private int pageSize;
    private Long totalPages;
    private Long totalElements;
    private boolean isLastPage;

}
