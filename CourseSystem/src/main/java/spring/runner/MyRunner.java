package spring.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import spring.entity.Course;
import spring.entity.Instructor;
import spring.entity.Student;
import spring.entity.User;
import spring.service.CourseService;
import spring.service.InstructorService;
import spring.service.RoleService;
import spring.service.StudentService;
import spring.service.UserService;

@Component
public class MyRunner implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private RoleService roleService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private InstructorService instructorService;
	
	@Autowired 
	private CourseService courseService;
	
	
	public static final String ADMIN = "Admin";
	
	public static final String STUDENT = "Student";
	
	public static final String INSTRUCTOR  = "Instructor";

	@Override
	public void run(String... args) throws Exception {
				
				User user = userService.createUser("admin@gmail.conm", "pass");
				User user1 = userService.createUser("instructors@gmail.conm", "pass2");
				
				roleService.createRole(ADMIN);
				roleService.createRole(INSTRUCTOR);
				roleService.createRole(STUDENT);
				
				userService.assignRoleToUser(user.getUserEmail(), ADMIN);
				userService.assignRoleToUser(user.getUserEmail(), INSTRUCTOR);
				userService.assignRoleToUser(user1.getUserEmail(), STUDENT);
				
				Instructor instructor1 = instructorService.createInstructor("Vanya", "Tutkins", "Mentor", "egor@gmail.com", "pass1");
				Instructor instructor2 = instructorService.createInstructor("Vova", "Pupkin", "Senior", "vova@gmail.com", "pass2");
				
				Student student1 = studentService.createStudent("Amir", "Ramitovich", "Java", "student@mail.com", "pass1");
				Student student2 = studentService.createStudent("Rahim", "Pantiks", "PHP", "sdutens2@gmail.com", "pass2");
				
				Course course1 = courseService.createCourse("Python", "10 hours", "Master Bottle", instructor1.getInstructorId());
				Course course2 = courseService.createCourse("Ruby", "8 hours", "Rubby on Rails", instructor2.getInstructorId());
				
				courseService.assignStudentToCourse(course1.getCourseId(), student1.getStudentId());
				courseService.assignStudentToCourse(course2.getCourseId(), student2.getStudentId());
		
			};
		
		
		
	}


