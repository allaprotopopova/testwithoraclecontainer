package com.jocker.presentation.reports.model.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Report {

    private Long id;
    private Long pid;
    private String title;
}
