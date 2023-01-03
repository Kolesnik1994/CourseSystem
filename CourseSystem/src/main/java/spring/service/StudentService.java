package spring.service;

import java.util.List;

import spring.entity.Student;

public interface StudentService {
	
	Student loadStudentById (Long id);
	
	List<Student> findStudentByName (String name);
	
	Student loadStudentByEmail (String email);
	
	Student createStudent (String firstName, String lastName, String level, String email, String password);
	
	Student updateStudent (Student student);
	
	List <Student> fetchStudent();
	
	void removeStudent (Long studentId);
	
	

}
