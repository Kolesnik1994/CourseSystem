package spring.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import spring.entity.Student;
import spring.service.impl.AbstractTest;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql (scripts = {"C:\\Users\\Java\\eclipse-workspace\\CourseSystem\\db\\clear.sql", "C:\\Users\\Java\\eclipse-workspace\\CourseSystem\\db\\insert.sql"})
public class StudentDaoTest extends AbstractTest {
		
	@Autowired
	private StudentDao studentDao;
	
	@Test
	void testFindStudentsByName () {
		List <Student> student = studentDao.findByfirstName("John");
		Long expected = 2L;
		
		assertEquals(expected, student.get(0).getStudentId());
		
	}
	
	@Test
	void testfindStudentByEmail () {
		
		Student student = new Student();
		student.setStudentId(1L);
		student.setStudentFirstName("Paul");
		student.setStudentLastName("Majaha");
		student.setStudentLevel("Pro");
		
		Student student1 = studentDao.findStudentByuserEmail("user@gmail.com");
		
		assertEquals(student, student1);
		
	}

}


