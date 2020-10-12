package com.jocker.presentation.personal.repository;

import com.jocker.presentation.core.AbstractTestcontainersTest;
import com.jocker.presentation.personal.MyBatisConfiguration;
import com.jocker.presentation.personal.model.dto.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class NotTestcontainersTest {


    @Test
    public void dummyTest() {
        assertThat(true).isTrue();
    }

}

