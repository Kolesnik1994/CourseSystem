package spring.web;

import java.util.List;

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
	public String getStudents (Model model, @RequestParam (name="keyword", defaultValue ="") String keyword) {
		List <Student> students = studentService.findStudentByName(keyword);
		model.addAttribute("listStudents", students);
		model.addAttribute("keyword", keyword);
		return "students/students";
			
	}
	
	@GetMapping ("/delete")
	public String deleteStudent (Long studentId, String keyword) {
		studentService.removeStudent(studentId);
		return "redirect:/students/index?keyword" + keyword;
	}
	
	@GetMapping ("/formUpdate")
	public String updateStudent (Model model, Long studentId) {
		Student student = studentService.loadStudentById(studentId);
		model.addAttribute("student", student);
		return "students/studentUpdate";
			
	}
	
	@PostMapping ("/update")
     public String update (Student student) {
		studentService.updateStudent(student);
		return  "redirect:/students/index";
   
	}  
	
	@GetMapping("/formCreate")
	public String formStudents (Model model) {
		model.addAttribute("student", new Student());
		return "students/studentsCreate";
				
	}
	
    @PostMapping ("/save")
	 public String save (Student student, BindingResult binding) {
    	User user = userService.loadUserByuserEmail(student.getUser().getUserEmail());
    	
    	if (user != null)
    		binding.rejectValue("user.userEmail", null, "There is already an accunt registered");
    	if (binding.hasErrors()) return "students/studentsCreate";
    	studentService.createStudent(student.getFirstName(), student.getLastName(), student.getLevel(), student.getUser().getUserEmail(), 
    	student.getUser().getUserPassword());
    	return "redirect://students/index";
}

}