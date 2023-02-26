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

/*
 * Data JPA Test for Student DAO layer, Run with Docker
 * If you will run test in another device, Change SQL scripts file path 
 * @Author VLadislav K
 */
@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql (scripts = {"file:C:\\Users\\Java\\git\\CourseSystem\\CourseSystem\\src\\test\\resources\\db\\clear.sql", "file:C:\\Users\\Java\\git\\CourseSystem\\CourseSystem\\src\\test\\resources\\db\\insert.sql"})
public class StudentDaoTest extends AbstractTest {
		
	@Autowired
	private StudentDao studentDao;
	
	@Test
	void testFindStudentsByName () {
		List <Student> student = studentDao.findByfirstName("Amir");
		Long expected = 1L;
		
		assertEquals(expected, student.get(0).getStudentId());
		
	}
	
	@Test
	void testFindStudentsByNameSize () {
		List <Student> student = studentDao.findByfirstName("Amir");
		assertEquals(1, student.size());
		
	}
	
	@Test
	void testFindStudentByEmail () {
		Student student = new Student();
		student.setStudentId(1L);
		student.setFirstName("Amir");
		student.setLastName("Ramitovich");
		student.setLevel("Java");
		
		Student student1 = studentDao.findStudentByuserEmail("student@mail.com");
		
		assertEquals(student, student1);
		
	}

}


