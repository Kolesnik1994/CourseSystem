package spring.web;


import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import spring.entity.Instructor;
import spring.entity.User;
import spring.service.InstructorService;
import spring.service.UserService;

@Controller
@RequestMapping ("/instructors")
public class InstructorController {
	
	
	private InstructorService instructorService;
	private UserService userService;
	public InstructorController(InstructorService instructorService, UserService userService) {
		this.instructorService = instructorService;
		this.userService = userService;
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
	
	@GetMapping ("/formUpdate")
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
	
	@GetMapping ("/formCreate")
	public String createInstructor(Model model) {
		model.addAttribute("instructor", new Instructor());
		return "views/instructorCreate";
	
	}
	
	@PostMapping ("/save")
	public String saves (@Valid Instructor instructor, BindingResult bindingResult) {
	User user = userService.loadUserByuserEmail(instructor.getUser().getUserEmail());
	if (user !=null) 
		bindingResult.rejectValue ("user.userEmail", null, "Acount already register with that email");
	if (bindingResult.hasErrors()) return "views/instructorCreate";
	instructorService.createInstructor(instructor.getFirstName(), instructor.getLastName(),instructor.getInstructorSummary(), 
		                                   instructor.getUser().getUserEmail(), instructor.getUser().getUserPassword());
		
		return "redirect:/instructors/index";
	}
	

	
	}
	



