package com.jocker.presentation.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final OracleContainerCustom oracleContainer = new OracleContainerCustom();

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        oracleContainer
                .withReuse(true)
                .waitingFor(Wait.forLogMessage("Database ready to use", 1));
        oracleContainer.start();

        TestPropertyValues.of(
                "spring.datasource.driver-class-name=" + oracleContainer.getDriverClassName(),
                "spring.datasource.url=" + oracleContainer.getJdbcUrl()
        ).applyTo(applicationContext);
    }
}
