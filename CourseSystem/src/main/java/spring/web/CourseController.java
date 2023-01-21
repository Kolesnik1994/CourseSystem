package spring.web;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import spring.service.CourseService;
import spring.service.InstructorService;

/**
 * Controller class
 * @author Vladislav Kolesnyk
 */
@Controller
@RequestMapping ("/courses")
public class CourseController {
	
	private CourseService courseService;
	private InstructorService instructorService;
	public CourseController(CourseService courseService, InstructorService instructorService) {
		this.courseService = courseService;
		this.instructorService = instructorService;	
	}
	
	
	@GetMapping ("/index")
	public String findCourse (Model model, @RequestParam (name="keyword", defaultValue = "") String keyword ) {
		List <Course> course = courseService.findCourseBycourseName(keyword);
		List<Course> course2 = courseService.fetchAll();
		model.addAttribute("listCourses", course);
		model.addAttribute("list", course2 );
		model.addAttribute("keyword", keyword);
		return "course/courses";
		
	}
	
	@GetMapping ("/delete")
	public String deleteCourse (Long courseId, String keyword) {
		courseService.removeCourse(courseId);
    return "redirect:/courses/index?keyword="+keyword;
	
	}
	
	@GetMapping ("/update")
	public String updateCourse (Model model, Long courseId) {
		Course course = courseService.loadCourseBycourseId(courseId);
		List <Instructor> instructors = instructorService.fetchInstrucotr();
		model.addAttribute("course", course);
		model.addAttribute("listInstructor", instructors);
		return "course/update";
	}
	
	@PostMapping ("/save")
	public String saveCourse (Course course) {
		courseService.createOrUpdateCourse(course);
		return "redirect:/courses/index";
	}
	
	@GetMapping ("/formCreate")
	public String formCourses (Model model) {
		List<Instructor> instructors = instructorService.fetchInstrucotr();
		model.addAttribute("listInstructor", instructors);
		model.addAttribute("course", new Course());
		return "course/formCreate";
		
		
	}
	
	@GetMapping ("/index/student")
	public String coursesForStudent (Model model) {
		Long studentId =1l;
		List<Course> courses = courseService.fetchCourseForstudentId(studentId);
		List<Course> other = courseService.fetchAll().stream().filter(course -> !courses.contains(course)).collect(Collectors.toList());
		model.addAttribute("listCourses", courses);
		model.addAttribute("otherCourses", other);
		return "course/student";
		
	}
	
	@GetMapping ("/enroll")
	public String enrollStudent(Long courseId) {
		Long studentId =1l;
		courseService.assignStudentToCourse(courseId, studentId);
		return "redirect:/courses/index/student";
				
		
	}
	
	
	@GetMapping ("/index/instructor")
	public String coursesForInstructor(Model model) {
		Long instructorId =1l; 
		Instructor instructor = instructorService.loadInstructorById(instructorId);
		model.addAttribute("listCourses",  instructor.getCourses());
		return "course/instructor";

		
	}
	
	@GetMapping ("/instructor")
	public String coursesByInstructorId (Model model, Long instructorId) {
		//Long instructorIds =1l;
		Instructor instructor = instructorService.loadInstructorById(instructorId);
		model.addAttribute("listCourses",  instructor.getCourses());
		return "course/instructor";
	
}
}
	

	


