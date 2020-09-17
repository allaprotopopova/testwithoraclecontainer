package com.jocker.presentation.reports;


import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assume.assumeTrue;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = MyBatisConfiguration.class, initializers = Initializer.class)
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
public abstract class AbstractTestcontainersTest {

    private static boolean enableTestContainer;

    static {

        try {
            String propertyOnTestContainers = System.getenv().get("enable-test-container");
            enableTestContainer = propertyOnTestContainers == null || Boolean.parseBoolean(propertyOnTestContainers);
        } catch (Exception e) {
            enableTestContainer = false;
            e.printStackTrace();
        }
    }

    @BeforeClass
    @BeforeAll
    public static void beforeClass() {
        assumeTrue(enableTestContainer);
    }
}