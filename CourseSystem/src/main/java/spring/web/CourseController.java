package spring.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		model.addAttribute("listCourses", course);
		model.addAttribute("keyword", keyword);
		return "views/courses";
		
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
		return "views/update";
	}
	
	@PostMapping ("/save")
	public String saveCourse (Course course) {
		courseService.createOrUpdateCourse(course);
		return "redirect:/courses/index";
	}
	
}
	

	


