package com.swe.student_teacher_panel;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class StudentTeacherPanelApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		assertNotNull(applicationContext, "Application context should load successfully");
	}

	@Test
	void applicationContextIsNotNull() {
		assertNotNull(applicationContext);
	}

	@Test
	void verifySpringBootApplicationStarts() {
		assertTrue(applicationContext.containsBean("studentTeacherPanelApplication"),
				"StudentTeacherPanelApplication bean should exist");
	}

}
