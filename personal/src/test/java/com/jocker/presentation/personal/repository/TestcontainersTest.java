package com.jocker.presentation.personal.repository;

import com.jocker.presentation.personal.AbstractTestcontainersTest;
import com.jocker.presentation.personal.model.dto.Employee;
import com.jocker.presentation.personal.repository.EmployeeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class TestcontainersTest extends AbstractTestcontainersTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void findAll() {
        assertThat(employeeRepository.findAll()).hasSize(8);
    }


    @Test
    public void save() {
        employeeRepository.save(new Employee().setPid(1L).setTitle("Кто-то новый"));
        assertThat(employeeRepository.findAll()).hasSize(9);
    }

    @Test
    public void findAllRecursive() {
        assertThat(employeeRepository.findAllRecursively()).isNotNull();
    }

}

