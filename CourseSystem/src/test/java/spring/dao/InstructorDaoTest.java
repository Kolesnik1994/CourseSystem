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

/*
 * Data JPA Test for Instructor DAO layer, Run with Docker
 * If you will run test in another device, Change SQL scripts file path 
 * @Author VLadislav K
 */
@DataJpaTest
@AutoConfigureTestDatabase	(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql (scripts = {"file:C:\\Users\\Java\\git\\CourseSystem\\CourseSystem\\src\\test\\resources\\db\\clear.sql", "file:C:\\Users\\Java\\git\\CourseSystem\\CourseSystem\\src\\test\\resources\\db\\insert.sql"})
public class InstructorDaoTest extends AbstractTest{
	
	@Autowired
	private InstructorDao instructorDao;
	
	@Test
	void testFindInstructorsByName() {
		List <Instructor> instructors = instructorDao.findInstructorByfirstName("Vanya");
		int expectedValue = 1;
		assertEquals(expectedValue, instructors.size());	
	}
	
	
	@Test
	void testFindInstructorsByEmail() {
		Instructor expectedInstructor = new Instructor();
		expectedInstructor.setInstructorId(1l);
		expectedInstructor.setFirstName("Vanya");
		expectedInstructor.setLastName("Tutkins");
		expectedInstructor.setInstructorSummary("Mentor");
		
		Instructor instructor = instructorDao.findInstructByuserEmail("egor@gmail.com");
		
		assertEquals(expectedInstructor, instructor);
	}
	
	@Test
	void testFindInstructorByNotExistingEmail() {
		Instructor instructor = instructorDao.findInstructByuserEmail("test@gmail.com");
		assertNull(instructor);
  }
}


