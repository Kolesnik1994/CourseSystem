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

/**
 * Controller class
 * @author VLadislav K
 */
@Controller
@RequestMapping("/students")
public class StudentController {
	
	private	StudentService studentService;
	private UserService userService;
	public StudentController(StudentService studentService, UserService userService) {
		this.studentService = studentService;
		this.userService = userService;
	}

	/**
     * user that have authority ADMIN can see list of students and search the by keyword @RequestParam   
     */
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
	
	/**
     * user that have authority ADMIN can remove students from list of students   
     */
	@GetMapping ("/delete")
	@PreAuthorize ("hasAuthority ('Admin')")
	public String deleteStudent (Long studentId, String keyword) {
		studentService.removeStudent(studentId);
		return "redirect:/students/index?keyword=" + keyword;
	}
	
	/**
     * user that have authority STUDENT can load update form.html 
     */
	@GetMapping ("/formUpdate")
	@PreAuthorize ("hasAuthority ('Student')")
	public String updateStudent (Model model, Principal principal) {
		Student student = studentService.loadStudentByEmail(principal.getName());
		model.addAttribute("student", student);
		return "students/studentUpdate";
			
	}
	
	/**
     * user that have authority STUDENT can update information about yourself   
     */
	@PostMapping ("/update")
	@PreAuthorize ("hasAuthority ('Student')")
     public String update (Student student) {
		studentService.updateStudent(student);
		return  "redirect:/courses/index/student";
   
	}  
	
	/**
     * user that have authority ADMIN can load html.form for create new Student   
     */
	@GetMapping("/formCreate")
	@PreAuthorize ("hasAuthority('Admin')")
	public String formStudents (Model model) {
		model.addAttribute("student", new Student());
		return "students/studentsCreate";
				
	}
	
	/**
     * user that have authority ADMIN can create new Student   
     */
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