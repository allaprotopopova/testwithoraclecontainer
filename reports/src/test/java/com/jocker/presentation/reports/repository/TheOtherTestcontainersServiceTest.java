package com.jocker.presentation.reports.repository;

import com.jocker.presentation.core.AbstractTestcontainersTest;
import com.jocker.presentation.reports.MyBatisConfiguration;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.api.AssertionsForInterfaceTypes;
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
    ReportRepository reportRepository;

    @Test
    public void findById() {
        AssertionsForClassTypes.assertThat(reportRepository.findById(2L)).isNotNull();
    }


    @Test
    public void findAll() {
        AssertionsForInterfaceTypes.assertThat(reportRepository.findAll()).hasSize(8);
    }

}

