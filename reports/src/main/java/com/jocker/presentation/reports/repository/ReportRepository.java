package com.jocker.presentation.reports.repository;

import com.jocker.presentation.reports.model.dto.Report;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportRepository {

   List<Report> findAll();

   Report findById(Long id);
}
