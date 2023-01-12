package spring.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.entity.Instructor;
import spring.service.InstructorService;

@Controller
@RequestMapping ("/instructors")
public class InstructorController {
	
	private InstructorService instructorService;
	public InstructorController(InstructorService instructorService) {
		this.instructorService = instructorService;
	}



	@GetMapping ("/index")
	public String getListInstructors (Model model, @RequestParam(name="keyword", defaultValue ="") String keyword) {
		List<Instructor> instructors = instructorService.findInstructorsByName(keyword);
		model.addAttribute(	"listInstructors", instructors);
		model.addAttribute("keyword", keyword);
		return "views/instructorList";
		
	}
	
	@GetMapping ("/delete")
	public String deleteInstructors (Long instructorId, String keyword) {
		instructorService.removeInstructor(instructorId);
		return "redirect:/instructors/index?keyword=" + keyword;
		
	}
	
	@GetMapping ("/update")
	public String updateInstructor (Model model, Long instructorId) {
		Instructor instructor = instructorService.loadInstructorById(instructorId);
		model.addAttribute("instructor", instructor);
		return "views/updateInstructor";
	}
	
	@PostMapping ("/update")
	public String update (Instructor instructor) {
		instructorService.updateInstructor(instructor);
		return "redirect:/instructors/index";
	}
	


}
