package com.strawhats.todoapp.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private String fieldValue;
    private Long fieldId;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(resourceName + " with " + fieldName + " : " + fieldValue + " not found");
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldId) {
        super(resourceName + " with " + fieldName + " : " + fieldId + " not found");
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldId = fieldId;
    }
}
