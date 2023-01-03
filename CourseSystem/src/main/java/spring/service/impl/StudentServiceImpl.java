package spring.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import spring.dao.StudentDao;
import spring.entity.Course;
import spring.entity.Student;
import spring.entity.User;
import spring.service.StudentService;
import spring.service.UserService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
	
	private StudentDao studentDao;

	private UserService userService;
	

	public StudentServiceImpl(StudentDao studentDao, UserService userService) {
		this.studentDao = studentDao;
		this.userService = userService;
	}


	@Override
	public Student loadStudentById(Long studentId) {
		return studentDao.findById(studentId).orElseThrow(()-> new EntityNotFoundException ("Student Not Found"));
	}
	
	
	@Override
	public List<Student> findStudentByName(String name) {
		return studentDao.findByfirstName(name);
	}

	@Override
	public Student loadStudentByEmail(String email) {
		return studentDao.findStudentByuserEmail(email);
	}

	@Override
	public Student createStudent(String firstName, String lastName, String level, String email, String password) {
		User user = userService.createUser(email, password);
		userService.assignRoleToUser(email, "Student");

		return studentDao.save(new Student (firstName, lastName, level, user));
	}

	@Override
	public Student updateStudent(Student student) {
		return studentDao.save(student);
		
	}

	@Override
	public List<Student> fetchStudent() {
		
		return studentDao.findAll();
	}

	@Override
	public void removeStudent(Long studentId) {
		
		Student student = loadStudentById (studentId);
		Iterator<Course> iterator = student.getCourses().iterator();
		if (iterator.hasNext()) {
			
			Course course = iterator.next();
			course.removeStudents(student);
			
			
		}
		
		studentDao.deleteById(studentId);
		
	}

}
