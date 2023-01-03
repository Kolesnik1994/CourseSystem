package spring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import spring.dao.CourseDao;
import spring.dao.InstructorDao;
import spring.dao.StudentDao;
import spring.entity.Course;
import spring.entity.Instructor;
import spring.entity.Student;
import spring.service.CourseService;

@Service
@Transactional // help avoid LazyInitializationException
public class CourseServiceImpl implements CourseService {

	private CourseDao courseDao;
	
	private InstructorDao instructorDao;
	
	private StudentDao studentDao;
	
	public CourseServiceImpl(CourseDao courseDao, InstructorDao instructorDao, StudentDao studentDao) {
		this.courseDao = courseDao;
		this.instructorDao = instructorDao;
		this.studentDao = studentDao;
	}

	@Override
	public Course loadCourseBycourseId(Long courseId) {
		return courseDao.findById(courseId).orElseThrow(()-> new EntityNotFoundException ("Course not Found"));
	}

	@Override
	public Course createCourse(String courseName, String courseDuration, String courseDescription, Long instructorId) {
		Instructor instructor =instructorDao.findById(instructorId).orElseThrow(() -> new EntityNotFoundException ("Instrucotr not Found"));
		return courseDao.save(new Course (courseName, courseDuration, courseDescription, instructor));
	}

	@Override
	public Course createOrUpdateCourse(Course course) {
		return courseDao.save(course);
	}

	@Override
	public List<Course> findCourseBycourseName(String keyword) {
		return courseDao.findCourseBycourseName(keyword);
	}

	@Override
	public void assignStudentToCourse(Long courseId, Long studentId) {
		
		Student student = studentDao.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student " +studentId + " Not Found"));
		Course course = courseDao.findById(courseId).orElseThrow(() -> new EntityNotFoundException ("Course" + courseId + " Not Found"));
		course.assignStudentToCourse(student);
		

	}

	@Override
	public List<Course> fetchAll() {
		return courseDao.findAll();
	}

	@Override
	public List<Course> fetchCourseForstudentId(Long studentId) {
		return courseDao.getCourseBystudentId(studentId);
	}

	@Override
	public void removeCourse(Long courseId) {
		courseDao.deleteById(courseId);
	
	}

}
