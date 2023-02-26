package spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.entity.Instructor;

/**
 * InstructorDAO layer
 * @author VLadislav K
 */

public interface InstructorDao extends JpaRepository <Instructor, Long> {
	
	@Query (value ="select i from Instructor as i where i.firstName like %:firstName% or i.lastName like %:firstName%")
	List <Instructor> findInstructorByfirstName (@Param ("firstName") String firstName);
	
	// select * from Instructor as i where i.user.userEmail =:userEmail
	// select * from courses as c where c.course_id in (select e.course_id from enrolled_in as e where e.student_id = :studentId)"
	
	@Query (value = "select i from Instructor as i where i.user.userEmail =:userEmail")
	Instructor findInstructByuserEmail (@Param ("userEmail") String userEmail);

}
