package com.jocker.presentation.reports.repository;

import com.jocker.presentation.reports.AbstractTestcontainersTest;
import com.jocker.presentation.reports.model.dto.Report;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
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

