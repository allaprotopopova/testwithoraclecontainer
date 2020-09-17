package com.jocker.presentation.reports;

import com.jocker.presentation.core.Initializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = MyBatisConfiguration.class)
class ReportsApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
