package spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.entity.Instructor;

public interface InstructorDao extends JpaRepository <Instructor, Long> {
	
	@Query ( value ="select i from Instructor as i where i.firstName like %:firstName% or i.lastName like %:firstName%")
	List <Instructor> findInstructorByfirstName (@Param ("firstName") String firstName);
	
	@Query (nativeQuery=true, value = "select i from Instructor as i where i.user.userEmail =:userEmail ")
	Instructor findInstructByuserEmail (@Param ("userEmail") String userEmail);

}
