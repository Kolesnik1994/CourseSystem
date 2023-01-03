package spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.entity.Course;

public interface CourseDao extends JpaRepository <Course, Long> {
	
	List <Course> findCourseBycourseName (String keyword);
	
    @Query (nativeQuery=true, value = "select * from courses as c where c.course_id in (select e.course_id from enrolled_in as e where e.student_id = :studentId)")
    List <Course> getCourseBystudentId (@Param ("studentId") Long studentId);

}
