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

import spring.entity.Student;
import spring.entity.User;
import spring.service.StudentService;
import spring.service.UserService;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	private	StudentService studentService;
	private UserService userService;
	public StudentController(StudentService studentService, UserService userService) {
		this.studentService = studentService;
		this.userService = userService;
	}

	@GetMapping ("/index")
	@PreAuthorize ("hasAuthority ('Admin')")
	public String getStudents (Model model, @RequestParam (name="keyword", defaultValue ="") String keyword) {
		List <Student> students = studentService.findStudentByName(keyword);
		List <Student> students2 = studentService.fetchStudent();
		model.addAttribute("listStudents", students);
		model.addAttribute("keyword", keyword);
		model.addAttribute("list", students2);
		return "students/students";
			
	}
	
	@GetMapping ("/delete")
	@PreAuthorize ("hasAuthority ('Admin')")
	public String deleteStudent (Long studentId, String keyword) {
		studentService.removeStudent(studentId);
		return "redirect:/students/index?keyword=" + keyword;
	}
	
	
	@GetMapping ("/formUpdate")
	@PreAuthorize ("hasAuthority ('Student')")
	public String updateStudent (Model model, Principal principal) {
		Student student = studentService.loadStudentByEmail(principal.getName());
		model.addAttribute("student", student);
		return "students/studentUpdate";
			
	}
	
	@PostMapping ("/update")
	@PreAuthorize ("hasAuthority ('Student')")
     public String update (Student student) {
		studentService.updateStudent(student);
		return  "redirect:/courses/index/student";
   
	}  
	
	@GetMapping("/formCreate")
	@PreAuthorize ("hasAuthority('Admin')")
	public String formStudents (Model model) {
		model.addAttribute("student", new Student());
		return "students/studentsCreate";
				
	}
	
    @PostMapping ("/save")
    @PreAuthorize ("hasAuthority ('Admin')")
	 public String save (Student student, BindingResult binding) {
    	User user = userService.loadUserByuserEmail(student.getUser().getUserEmail());
    	
    	if (user != null)
    		binding.rejectValue("user.userEmail", null, "There is already an accunt registered");
    	if (binding.hasErrors()) return "students/studentsCreate";
    	studentService.createStudent(student.getFirstName(), student.getLastName(), student.getLevel(), student.getUser().getUserEmail(), 
    	student.getUser().getUserPassword());
    	return "redirect:/students/index";
}

}