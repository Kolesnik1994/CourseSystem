package spring.service;

import java.util.List;

import spring.entity.Instructor;

public interface InstructorService {
	
	Instructor loadInstructorById (Long instructorId);
	
	List<Instructor> findInstructorsByName (String name);
	
	Instructor loadInstrucotrByEmail(String userEmail);
	
	Instructor createInstructor (String firstName, String lastName, String summary, String userEmail, String password);
	
	Instructor updateInstructor (Instructor instructor);
	
	List <Instructor> fetchInstrucotr();
	
	void removeInstructor (Long instructorId);
	
	

}
