package spring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import spring.dao.InstructorDao;
import spring.dao.UserDao;
import spring.entity.Course;
import spring.entity.Instructor;
import spring.entity.User;
import spring.service.CourseService;
import spring.service.InstructorService;
import spring.service.UserService;

/**
 * Instructor Service class that implement InstructorSerivce interface and consist service logic
 * @author VLadislav K
 */

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

	private InstructorDao instructorDao;
    
	private CourseService courseService;

	private UserService userService;
	
    private UserDao userDao;  
    
    public InstructorServiceImpl(InstructorDao instructorDao, UserService userService, UserDao userDao, CourseService courseService) {
		this.instructorDao = instructorDao;
		this.userService=userService;
		this.userDao=userDao;
		this.courseService=courseService;
	}
	
	@Override
	public Instructor loadInstructorById(Long instructorId) {
		return instructorDao.findById(instructorId).orElseThrow(()-> new EntityNotFoundException ("Instructor not found"));
	}

	@Override
	public List<Instructor> findInstructorsByName(String name) {
		return instructorDao.findInstructorByfirstName(name);
	}

	@Override
	public Instructor loadInstrucotrByEmail(String userEmail) {
		return instructorDao.findInstructByuserEmail(userEmail);
	}

	@Override
	public Instructor createInstructor(String firstName, String lastName, String summary, String userEmail, String password) {
			
		 User user2 = userService.createUser(userEmail, password);
		 userService.assignRoleToUser(userEmail, "Instructor");
		 userDao.save(user2);
		 return instructorDao.save(new Instructor (firstName, lastName, summary, user2));
		 
	}

	@Override
	public Instructor updateInstructor(Instructor instructor) {
		return instructorDao.save(instructor);
	}

	@Override
	public List<Instructor> fetchInstrucotr() {
		return instructorDao.findAll();
	}

	@Override
	public void removeInstructor(Long instructorId) {
		Instructor instructor = loadInstructorById(instructorId);
		for (Course course : instructor.getCourses()) {
			courseService.removeCourse(course.getCourseId());	
		}
		
		instructorDao.deleteById(instructorId);
		
	}
	
}
