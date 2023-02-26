package spring.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;


/**
 * MySQL Test that check connect to our database in container use our property 
 * @author VLadislav K
 */

public class AbstractTest {
	
	private static MySQLContainer container = new MySQLContainer<>("mysql:latest")
			.withReuse(true);
			
	
	@DynamicPropertySource
	public static void ovverideProps(DynamicPropertyRegistry registry) {
		registry.add("jdbc:mysql://localhost:3306/cursesystem", container::getJdbcUrl);
		registry.add("root", container::getUsername);
		registry.add("123456789Vv", container::getPassword);
	}
	
	@BeforeAll
	public static void setUp() {
	container.start();
	}
	
	

}
