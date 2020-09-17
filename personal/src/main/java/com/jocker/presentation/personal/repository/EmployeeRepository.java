package com.jocker.presentation.personal.repository;

import com.jocker.presentation.personal.model.dto.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeRepository{

    List<Employee> findAllRecursively();

    List<Employee> findAll();

    void save(Employee setTitle);
}
