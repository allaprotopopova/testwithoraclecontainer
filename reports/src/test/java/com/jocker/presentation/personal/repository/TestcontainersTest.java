package com.jocker.presentation.reports.repository;

import com.jocker.presentation.reports.AbstractTestcontainersTest;
import com.jocker.presentation.reports.model.dto.Report;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class TestcontainersTest extends AbstractTestcontainersTest {

    @Autowired
    ReportRepository reportRepository;

    @Test
    public void findById() {
        assertThat(reportRepository.findById(2L)).isNotNull();
    }


    @Test
    public void findAll() {
        assertThat(reportRepository.findAll()).hasSize(8);
    }

}

