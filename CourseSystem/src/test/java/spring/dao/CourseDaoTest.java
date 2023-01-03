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

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql (scripts = {"C:\\Users\\Java\\eclipse-workspace\\CourseSystem\\db\\clear.sql", "C:\\Users\\Java\\eclipse-workspace\\CourseSystem\\db\\insert.sql	"})
class CourseDaoTest extends AbstractTest{
	
	@Autowired
	private CourseDao courseDao;
	
	@Test
    void findCoursesByCourseNameContains() {
		List <Course> courses = courseDao.findCourseBycourseName("Rubby");
		int expectedResult = 2;
		assertEquals(expectedResult, courses.size());
		
}
	
	@Test
	void getCoursesByStudentId () {
		List <Course> courses = courseDao.getCourseBystudentId(1L);
		int expectedResult = 1;
		assertEquals(expectedResult, courses);
		
	}
	
}
