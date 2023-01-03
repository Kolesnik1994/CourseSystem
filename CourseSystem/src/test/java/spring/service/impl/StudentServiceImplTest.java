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
import spring.dao.CourseDao;
import spring.dao.StudentDao;
import spring.entity.Course;
import spring.entity.Student;
import spring.entity.User;
import spring.service.CourseService;
import spring.service.StudentService;
import spring.service.UserService;

/**
 * Junit Test
 * @author Vladislav Kolesnyk
 */

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {
	
	@InjectMocks
	private StudentServiceImpl studentService;
	
	@Mock
	private StudentDao studentDao;
	
	@Mock
	private UserService userService;
	
	
	/**
	 * create Student entity and set Id
	 * find Student entity by ID with Mock studentDao with any parameter -> method findById(), and return Optional student 
	 * call service method, that allows find Student by Id, method -> loadStudentById();
	 * equals both variables by method asserEquals(Object expected, Object actual)
	 */
	
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
	 * throw Exception -> EntityNotFoundException by executable method -> loadStudentById()
	 */
		
	@Test
	void testExceptionForNotFoundExceptionUserById() {
	assertThrows(EntityNotFoundException.class, ()-> studentService.loadStudentById(any()));
	
	}

	/**
	 * find student by name in Service Layer
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

	/**
	 * create entity and set email and password
	 * use Mock StudentService to create user and use mockito static method -> to return object user
	 * create Student is Service layer
	 * verify if Mock studentDao execute method -> save();
	 */
	
	
	@Test
	void testCreateStudent() {
		User user = new User();
		user.setUserEmail("user@gmail.com");
		user.setUserPassword("pass");
		
		 when(userService.createUser(any(), any())).thenReturn(user);
		 studentService.createStudent("Lucky", "God's", "Sun", "user@gmail.com", "pass1");
		 verify(studentDao).save(any());
	
	}
  
	/**
	 * create Student entity
	 * set Student id
	 * update Student in service layer 
	 * verify if Mock StudentDao called method -> save() 
	 */
	
	@Test
	void testUpdateStudent() {
		Student student = new Student();
		student.setStudentId(1L);
		studentService.updateStudent(student);
		verify(studentDao).save(student);
		
		
	}

	/**
	 * fetch all student in service layer
	 * verify if Mock StudentDao fetch all students 1 times
	 */
	@Test
	void testFetchStudent() {
		studentService.fetchStudent();
		verify(studentDao, times(1)).findAll();

	}

	
	/**
	 * create entity Student and set Id
	 * create entity Course and set Id
	 * get course from student and add course
	 * find student by id -> return student object
	 * remove student in Service layer
	 * verify if Mock studentDao delete student by id -> deleteById()
	 */
	
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
