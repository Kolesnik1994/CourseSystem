package spring.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import spring.entity.Instructor;
import spring.service.impl.AbstractTest;

@DataJpaTest
@AutoConfigureTestDatabase	(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"C:\\Users\\Java\\eclipse-workspace\\CourseSystem\\db\\clear.sql", "C:\\Users\\Java\\eclipse-workspace\\CourseSystem\\db\\insert.sql"})

public class InstructorDaoTest extends AbstractTest{
	
	@Autowired
	private InstructorDao instructorDao;
	
	@Test
	void testFindInstructorsByName() {
		List <Instructor> instructors = instructorDao.findInstructorByfirstName("steve");
		int expectedValue = 1;
		assertEquals(expectedValue, instructors);
	}
	
	
	@Test
	void testFindInstructorsByEmail() {
		Instructor expectedInstructor = new Instructor();
		expectedInstructor.setInstructorId(1l);
		expectedInstructor.setInstructorFirstName("Jorda");
		expectedInstructor.setInstructorLastName("Ddd");
		expectedInstructor.setInstructorSummary("Method");
		
		
		Instructor instructor = instructorDao.findInstructByuserEmail("user@gmail.com");
		
		assertEquals(expectedInstructor, instructor);
	}
	
	@Test
	void testFindInstructorByNotExistingEmail() {
		Instructor instructor = instructorDao.findInstructByuserEmail("user@gmail.com");
		assertNull(instructor);
	

}
}
