package spring.service;

import java.util.List;

import spring.entity.Course;

public interface CourseService {
	
	Course loadCourseBycourseId (Long courseId);
	
	Course createCourse (String courseName, String courseDuration, String courseDescription, Long instructorId);
	
	Course createOrUpdateCourse(Course course);
	
	List <Course> findCourseBycourseName(String keyword);
	
	void assignStudentToCourse (Long courseId, Long studentId);
	
	List <Course> fetchAll();
	
	List <Course> fetchCourseForstudentId (Long studentId);
	
	void removeCourse (Long courseId);

}
