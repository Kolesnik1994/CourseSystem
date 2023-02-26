package spring.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import spring.entity.Course;
import spring.service.impl.AbstractTest;

/*
 * Data JPA Test for Course DAO layer, Run with Docker!
 * If you will run test in another device, Change SQL scripts file path 
 * @Author VLadislav K
 */
@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql (scripts = {"file:C:\\Users\\Java\\git\\CourseSystem\\CourseSystem\\src\\test\\resources\\db\\clear.sql", "file:C:\\Users\\Java\\git\\CourseSystem\\CourseSystem\\src\\test\\resources\\db\\insert.sql"})
class CourseDaoTest extends AbstractTest{
	
	@Autowired
	private CourseDao courseDao;
	
	@Test
    void findCoursesByCourseNameContains() {
		List <Course> courses = courseDao.findCourseBycourseName("Ruby");
		int expectedResult = 1;
		assertEquals(expectedResult, courses.size());		
}
	
	@Test
	void getCoursesByStudentId () {
		List <Course> courses = courseDao.getCourseBystudentId(1L);
		int expected = 1;
		assertEquals(expected, courses.size());	
	}
}
