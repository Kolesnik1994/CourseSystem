package spring.web;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.dao.CourseDao;
import spring.entity.Course;
import spring.entity.Instructor;
import spring.entity.User;
import spring.service.CourseService;
import spring.service.InstructorService;
import spring.service.UserService;

/**
 * Controller class 
 * @author VLadislav Kolesnyk
 */
@Controller
@RequestMapping ("/courses")
public class CourseController {
	
	private CourseService courseService;
	private InstructorService instructorService;
	private UserService userService;
	
	public CourseController(CourseService courseService, InstructorService instructorService, UserService userService) {
		this.courseService = courseService;
		this.instructorService = instructorService;
		this.userService = userService;
	}

	/**
	 * Get list of Student for user that have authority ADMIN, also you can search course @RequestParam 
	 */
	@GetMapping ("/index")
	@PreAuthorize ("hasAuthority ('Admin')")
	public String findCourse (Model model, @RequestParam (name="keyword", defaultValue = "") String keyword ) {
		List <Course> course = courseService.findCourseBycourseName(keyword);
		List<Course> course2 = courseService.fetchAll();
		model.addAttribute("listCourses", course);
		model.addAttribute("list", course2 );
		model.addAttribute("keyword", keyword);
		return "course/courses";	
	}
	
	/**
	 *  user that have authority ADMIN can delete course  
	 */
	@GetMapping ("/delete")
	@PreAuthorize ("hasAuthority ('Admin')")
	public String deleteCourse (Long courseId, String keyword) {
		courseService.removeCourse(courseId);
    return "redirect:/courses/index?keyword="+keyword;
	}
	
	/**
	 *  user that have authority ADMIN & INSTRUCTOR can update information about course
	 *  !INSTRUCOTR can'not change information about instructor mapped to course   
	 */
	@GetMapping ("/update")
	@PreAuthorize ("hasAnyAuthority ('Admin','Instructor')")
	public String updateCourse (Model model, Long courseId, Principal principal) {
	if (userService.doesUserHasRole("Instructor")) {
		Instructor instructor = instructorService.loadInstrucotrByEmail(principal.getName());
		model.addAttribute("currentInstructor", instructor);
	}
		Course course = courseService.loadCourseBycourseId(courseId);
		List <Instructor> instructors = instructorService.fetchInstrucotr();
		model.addAttribute("course", course);
		model.addAttribute("listInstructor", instructors);
		return "course/update";
	}
	
	/**
	 *  user that have authority ADMIN & INSTRUCTOR can save new changes about courses  
	 */
	@PostMapping ("/save")
	@PreAuthorize ("hasAnyAuthority ('Admin','Instructor')")
	public String saveCourse (Course course) {
		courseService.createOrUpdateCourse(course);
		return userService.doesUserHasRole("Instructor") ? "redirect:/courses/index/instructor" : "redirect:/courses/index";
	}
	
	/**
	 *  user that have authority ADMIN & INSTRUCTOR can create new Course
	 */
	@GetMapping ("/formCreate")
	@PreAuthorize ("hasAnyAuthority ( 'Admin' , 'Instructor')")
	public String formCourses (Model model, Principal principal) {
		if (userService.doesUserHasRole("Instructor")) {
			Instructor instructor = instructorService.loadInstrucotrByEmail(principal.getName());
			model.addAttribute("currentInstructor", instructor);	
		}	
		List<Instructor> instructors = instructorService.fetchInstrucotr();
		model.addAttribute("listInstructor", instructors);
		model.addAttribute("course", new Course());
		return "course/formCreate";	
	}
	
	/**
	 *  user that have authority Student can see yourself courses, and enroll for new course
	 */
	@GetMapping ("/index/student")
	@PreAuthorize("hasAuthority ('Student')")
	public String coursesForStudent (Model model, Principal principal) {
		User user = userService.loadUserByuserEmail(principal.getName());
		List<Course> courses = courseService.fetchCourseForstudentId(user.getStudent().getStudentId());
		List<Course> other = courseService.fetchAll().stream().filter(course -> !courses.contains(course)).collect(Collectors.toList());
		model.addAttribute("listCourses", courses);
		model.addAttribute("otherCourses", other);
		model.addAttribute("firstName", user.getStudent().getFirstName());
		model.addAttribute("lastName", user.getStudent().getLastName());
		return "course/student";
	}
	
	/**
	 *  user that have authority Student can enroll for new course
	 */
	@GetMapping ("/enroll")
	@PreAuthorize("hasAuthority ('Student')")
	public String enrollStudent(Long courseId, Principal principal) {
		User user = userService.loadUserByuserEmail(principal.getName());
		courseService.assignStudentToCourse(courseId, user.getStudent().getStudentId());
		return "redirect:/courses/index/student";
				
		
	}
	
	/**
	 *  user that have authority Instructor can see courses that he must teaches
	 */
	@GetMapping ("/index/instructor")
	@PreAuthorize ("hasAuthority ('Instructor')")
	public String coursesForInstructor(Model model, Principal principal) {
		User user = userService.loadUserByuserEmail(principal.getName()) ;
		Instructor instructor = instructorService.loadInstructorById(user.getInstructor().getInstructorId());
		model.addAttribute("listCourses",  instructor.getCourses());
		model.addAttribute("firstName", instructor.getFirstName());
		model.addAttribute("lastName", instructor.getLastName());
		return "course/instructor";
		
	}
	
   /**
    * user that have authority ADMIN can see information about instructor's courses and update it
    */
	@GetMapping ("/instructor")
	@PreAuthorize ("hasAuthority ('Admin')")
	public String coursesByInstructorId (Model model, Long instructorId) { 
		Instructor instructor = instructorService.loadInstructorById(instructorId);
		model.addAttribute("listCourses",  instructor.getCourses());
		model.addAttribute("firstName", instructor.getFirstName());
		model.addAttribute("lastName", instructor.getLastName());
		return "course/instructor";
   }
}
	

	


