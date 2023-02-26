package spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for unauthorized users
 * @author VLadislav K
 */
@Controller
public class SecurityController {
	
	@GetMapping("/403")
	public String nonAuthorized() {
		
		return "403";
	}

}
