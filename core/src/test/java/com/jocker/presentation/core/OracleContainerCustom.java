package com.jocker.presentation.core;

import org.testcontainers.containers.OracleContainer;

public class OracleContainerCustom extends OracleContainer {

    public OracleContainerCustom() {
        super();
        withStartupTimeoutSeconds(30);
        withConnectTimeoutSeconds(30);
    }

    @Override
    public String getJdbcUrl() {
        return String.format("jdbc:oracle:thin:@%s:%s:ee", getHost(), getOraclePort());
    }
}
