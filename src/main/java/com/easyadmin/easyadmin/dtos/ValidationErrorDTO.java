package com.easyadmin.easyadmin.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO extends CustomErrorDTO{

    List<FieldMessageDTO> errors = new ArrayList<>();

    public ValidationErrorDTO(LocalDateTime timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessageDTO> getErrors() {
        return errors;
    }    

    public void addError(FieldMessageDTO error) {
        errors.removeIf(x -> x.getFieldName().equals(error.getFieldName()));
        errors.add(error);
    }
    
}
