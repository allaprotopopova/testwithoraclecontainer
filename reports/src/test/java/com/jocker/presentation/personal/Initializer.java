package com.jocker.presentation.reports;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final OracleContainer oracleContainer = new OracleContainer();

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        oracleContainer
                .withStartupTimeoutSeconds(90)
                .withConnectTimeoutSeconds(90)
                .withReuse(true)
                .waitingFor(Wait.forLogMessage("Database ready to use", 1));
        oracleContainer.start();

        //String jdbcUrl = "jdbc:oracle:thin:" + oracleContainer.getUsername() + "/" + oracleContainer.getPassword() + "@" + oracleContainer.getContainerIpAddress() + ":" + oracleContainer.getOraclePort() + ":ee";
        TestPropertyValues.of(
                "spring.datasource.driver-class-name=" + oracleContainer.getDriverClassName(),
                "spring.datasource.url=" + oracleContainer.getJdbcUrl()
        ).applyTo(applicationContext);
    }
}
