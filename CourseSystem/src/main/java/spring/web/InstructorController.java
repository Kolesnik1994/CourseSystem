package spring.web;


import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize ("hasAuthority ('Admin')")
	public String getListInstructors (Model model, @RequestParam(name="keyword", defaultValue ="") String keyword) {
		List<Instructor> instructors = instructorService.findInstructorsByName(keyword);
		model.addAttribute(	"listInstructors", instructors);
		model.addAttribute("keyword", keyword);
		return "instractor/instructorList";
		
	}
	
	@GetMapping ("/delete")
	@PreAuthorize ("hasAuthority ('Admin')")
	public String deleteInstructors (Long instructorId, String keyword) {
		instructorService.removeInstructor(instructorId);
		return "redirect:/instructors/index?keyword=" + keyword;
		
	}
	
	@GetMapping ("/formUpdate")
	@PreAuthorize ("hasAuthority ('Instructor')")
	public String updateInstructor (Model model, Principal principal) {
		Instructor instructor = instructorService.loadInstrucotrByEmail(principal.getName());
		model.addAttribute("instructor", instructor);
		return "instractor/updateInstructor";
	}
	
	@PostMapping ("/update")
	@PreAuthorize ("hasAuthority ('Instructor')")
	public String update (Instructor instructor) {
		instructorService.updateInstructor(instructor);
		return "redirect:/courses/index/instructor";
	}
	
	@GetMapping ("/formCreate")
	@PreAuthorize ("hasAuthority ('Admin')")
	public String createInstructor(Model model) {
		model.addAttribute("instructor", new Instructor());
		return "instractor/instructorCreate";
	
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
	



