package spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home Controller for first page to all users
 */
@Controller
public class HomeController {
	
	@GetMapping ("/")
	public String home () {
		return "home";
	}
	

}
