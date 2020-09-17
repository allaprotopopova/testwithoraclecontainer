package com.jocker.presentation.personal.repository;

import com.jocker.presentation.core.AbstractTestcontainersTest;
import com.jocker.presentation.personal.MyBatisConfiguration;
import com.jocker.presentation.personal.model.dto.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ContextConfiguration(classes = MyBatisConfiguration.class)
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
public class TheOtherTestcontainersServiceTest extends AbstractTestcontainersTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void findAllTest() {
        assertThat(employeeRepository.findAll()).hasSize(8);
    }

    @Test
    public void saveTest() {
        employeeRepository.save(new Employee().setPid(1L).setTitle("Кто-то новый"));
        assertThat(employeeRepository.findAll()).hasSize(9);
    }


    @Test
   public void findAllRecursiveTest() {

        assertThat(employeeRepository.findAllRecursively()).isNotNull();
    }

}

