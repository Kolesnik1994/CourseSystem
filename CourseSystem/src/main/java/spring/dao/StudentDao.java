package spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.entity.Student;

public interface StudentDao extends JpaRepository <Student, Long> {
	
	@Query (value ="select s from Student as s where s.firstName like %:firstName% or s.lastName like %:firstName%")
	List <Student> findByfirstName(String firstName);
	
	@Query (value = "select s from Student as s where s.user.userEmail =:userEmail")
	Student findStudentByuserEmail ( @Param ("userEmail") String userEmail);

}
