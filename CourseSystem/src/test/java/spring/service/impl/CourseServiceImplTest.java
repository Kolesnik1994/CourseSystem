package spring.service.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;
import spring.dao.CourseDao;
import spring.dao.InstructorDao;
import spring.dao.StudentDao;
import spring.entity.Course;
import spring.entity.Instructor;
import spring.entity.Student;

/**
 * Course Service Layer Testing
 * @author VLadislav K
 */
@ExtendWith(MockitoExtension.class)
public class CourseServiceImplTest {
	
	@Mock
	private CourseDao courseDao;
	
	@Mock
	private InstructorDao instructorDao;
	
	@Mock
	private StudentDao studentDao;
	
	@InjectMocks
	private CourseServiceImpl courseService;
	
	@Test
	void testLoadCourseBycourseId() {
		Course course = new Course();
		course.setCourseId(1L);
		when(courseDao.findById(any())).thenReturn(Optional.of(course));  // clrt + space = you choose method that you need
		
		Course actualcourse = courseService.loadCourseBycourseId(1L);
		
		assertEquals (course, actualcourse);
		
	}
	
	@Test
	void testExceptionForNotFoundCOurseById	() {
		assertThrows(EntityNotFoundException.class,()-> courseService.loadCourseBycourseId(2L));
	}

	@Test
	void testCreateCourse() {
		Instructor instructor = new Instructor ();
		instructor.setInstructorId(1L);
		
		when(instructorDao.findById(any())).thenReturn(Optional.of(instructor));
		
		courseService.createCourse("JPA", "1 hour", "Spring Boot JPA", 1L);
		verify(courseDao).save(any());
	}

	@Test
	void testCreateOrUpdateCourse() {
		Course course = new Course();
		course.setCourseId(1L);
		courseService.createOrUpdateCourse(course);
		
		ArgumentCaptor<Course> argumentCaptor = ArgumentCaptor.forClass(Course.class);
		verify(courseDao).save(argumentCaptor.capture());
		
		Course capturedValue = argumentCaptor.getValue();
		assertEquals(course,capturedValue );
	}

	@Test
	void testFindCourseBycourseName() {
		String coursename = "JPA";
		courseService.findCourseBycourseName(coursename);
		verify(courseDao).findCourseBycourseName(coursename);
		
	}

	@Test
	 void testAssignStudentToCourse() {
		Student student = new Student();
		student.setStudentId(2L);
		
		Course course = new Course();
		course.setCourseId(1L);
		
		when(courseDao.findById(any())).thenReturn(Optional.of(course));
		when(studentDao.findById(any())).thenReturn(Optional.of(student));
		
		courseService.assignStudentToCourse(1L, 1l);
		
		
	}

	@Test
	void testFetchAll() {
		courseService.fetchAll();
		verify(courseDao).findAll();
	}

	@Test
	void testFetchCourseForstudentId() {
		courseService.fetchCourseForstudentId(1L);
		verify(courseDao).getCourseBystudentId(1L);
	}

	@Test
	void testRemoveCourse() {
		courseService.removeCourse(1L);
		verify(courseDao).deleteById(1l);
	}

}
