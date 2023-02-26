package spring.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;
import spring.dao.CourseDao;
import spring.dao.InstructorDao;
import spring.dao.UserDao;
import spring.entity.Course;
import spring.entity.Instructor;
import spring.entity.User;
import spring.service.CourseService;
import spring.service.UserService;

/**
 * Testing Instructor Service Layer
 * @author VLadislav L
 */
@ExtendWith(MockitoExtension.class)
public class InstractorServiceImplTest {
	
	@InjectMocks
	private InstructorServiceImpl instructorServ;
	
	@Mock
	private InstructorDao instructorDao;
	
	@Mock
	private UserService userServ;
	
	@Mock
	private UserDao userDao;
	
	@Mock
	private CourseDao courseDao;
	
	@Mock
	private CourseService courseServ;

	@Test
	 void testLoadInstructorById()  {
		Instructor instructor = new Instructor();
		instructor.setInstructorId(1L);
		when(instructorDao.findById(any())).thenReturn(Optional.of(instructor));
		
		Instructor actualInstructor = instructorServ.loadInstructorById(1L);
		assertEquals(instructor, actualInstructor);
		
	}
	
	@Test
	void testExceptionForNotFoundInstructorById () {
		assertThrows(EntityNotFoundException.class,()-> instructorServ.loadInstructorById(1L));
	}
	
	
	@Test
	void testFindInstructorByName () {
		String instructorname = "Vika";
		instructorServ.findInstructorsByName(instructorname);
		verify(instructorDao).findInstructorByfirstName(instructorname);
	}

	@Test
	void  testLoadInstructorByEmail () {
		String instructorEmail = "kush@gmail.com";
		instructorServ.loadInstrucotrByEmail(instructorEmail);
		verify(instructorDao).findInstructByuserEmail(instructorEmail);
	}
	
	@Test
	void testCreateInstructor () { 
		User user = new User ("usr@1gmail.com", "pass1");
		userDao.save(user);
	    when(userServ.createUser(any(), any())).thenReturn(user);
	    
	    instructorServ.createInstructor("Irsa","Svoboda", "Maste", "usr@1gmail.com", "pass1" );
	    verify(instructorDao).save(any());
		
	}
	
	@Test
	void testUpdateInstructor () {	
		Instructor instructor = new Instructor();
		instructor.setInstructorId(1L);
		instructorServ.updateInstructor(instructor);
		verify (instructorDao).save(instructor);
		
	}
	
	@Test
	void testFetchInstructor() {
		instructorServ.fetchInstrucotr();
		verify(instructorDao).findAll();
	}
	
	@Test
	void testRemoveInstructor() {
		
		Instructor inst = new Instructor();
		inst.setInstructorId(1L);
		
		Course course = new Course();
		course.setCourseId(1L);
		
		inst.getCourses().add(course);
		
		when(instructorDao.findById(1L)).thenReturn(Optional.of(inst));
		
		instructorServ.removeInstructor(1L);
		
		verify(courseServ).removeCourse(any());
		verify(instructorDao).deleteById(1L);
	
	}
		
}
