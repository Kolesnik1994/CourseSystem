package spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan ("spring.entity")
@SpringBootApplication
public class CourseSystemApplication { 
	

	public static void main(String[] args) {
		SpringApplication.run(CourseSystemApplication.class, args);
	
	  }
	
	}
		


	


