package spring.service;

import java.util.List;

import spring.entity.Instructor;

/*
 * Course Interface that can used for different purpose (testing, service layer and other)
 * @Author VLadislav K
 */

public interface InstructorService {
	
	Instructor loadInstructorById (Long instructorId);
	
	List<Instructor> findInstructorsByName (String keyword);
	
	Instructor loadInstrucotrByEmail(String userEmail);
	
	Instructor createInstructor (String firstName, String lastName, String summary, String userEmail, String password);
	
	Instructor updateInstructor (Instructor instructor);
	
	List <Instructor> fetchInstrucotr();
	
	void removeInstructor (Long instructorId);
	
	

}
