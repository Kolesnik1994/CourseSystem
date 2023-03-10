	package spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * main class that run application
 * @author VLadislav K
 */
@EntityScan ("spring.entity")
@SpringBootApplication
@EnableMethodSecurity  (prePostEnabled=true, securedEnabled=true )
public class CourseSystemApplication { 
	

	public static void main(String[] args) {
		SpringApplication.run(CourseSystemApplication.class, args);
		
	}	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	
  }
	}
		





