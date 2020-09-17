package com.jocker.presentation.personal.model.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Employee {

    private Long id;
    private Long pid;
    private String title;
}
