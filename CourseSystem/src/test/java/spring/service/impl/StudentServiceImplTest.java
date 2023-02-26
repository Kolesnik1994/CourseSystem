package spring.service.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;
import spring.dao.StudentDao;
import spring.entity.Course;
import spring.entity.Student;
import spring.entity.User;
import spring.service.UserService;

/**
 * Testing Student Service layer
 * @author VLadislav K
 */
@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {
	
	@InjectMocks
	private StudentServiceImpl studentService;
	
	@Mock
	private StudentDao studentDao;
	
	@Mock
	private UserService userService;
	
	@Test
	void testLoadStudentById() {
		Student student = new Student();
		student.setStudentId(1L);
		
		when(studentDao.findById(any())).thenReturn(Optional.of(student));
		
		Student actualstudent = studentService.loadStudentById(1L);
		assertEquals(student, actualstudent);
		
	}

	/**
	 * check if our service layer with his method throws the correct error 
	 */
		
	@Test
	void testExceptionForNotFoundExceptionUserById() {
	assertThrows(EntityNotFoundException.class, ()-> studentService.loadStudentById(any()));
	
	}

	/**
	 * verify if Mock StudentDao find student by name, method -> findByfirstName();
	 */
	
	@Test
	void testFindStudentByName() {
		String name = "John";
		studentService.findStudentByName(name);
	    verify(studentDao).findByfirstName(name);
		 
	}

	/**
	 * find student by email in Service Layer
	 * verify if Mock StudentDao find student by email, method -> findStudentByuserEmail();
	 */
	
	@Test
	void testLoadStudentByEmail() {
		String email = "user@gmail.com";
		studentService.loadStudentByEmail(email);
		verify(studentDao).findStudentByuserEmail(email);
		
	}

	@Test
	void testCreateStudent() {
		User user = new User();
		user.setUserEmail("user@gmail.com");
		user.setUserPassword("pass");
		
		when(userService.createUser(any(), any())).thenReturn(user);
		studentService.createStudent("Lucky", "God's", "Sun", "user@gmail.com", "pass1");
		verify(studentDao).save(any());
	
	}
  
	@Test
	void testUpdateStudent() {
		Student student = new Student();
		student.setStudentId(1L);
		studentService.updateStudent(student);
		verify(studentDao).save(student);
		
		
	}

	@Test
	void testFetchStudent() {
		studentService.fetchStudent();
		verify(studentDao, times(1)).findAll();

	}
	
	@Test
	void testRemoveStudent() {
		Student student = new Student();
		student.setStudentId(1L);
		
		Course course = new Course();
		course.setCourseId(1L);
		student.getCourses().add(course);
		
		when(studentDao.findById(any())).thenReturn(Optional.of(student));
	    studentService.removeStudent(1l);
		
		verify(studentDao).deleteById(1L);
	
	}
}
