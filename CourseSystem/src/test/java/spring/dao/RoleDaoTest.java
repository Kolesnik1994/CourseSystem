package spring.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import spring.entity.Role;
import spring.service.impl.AbstractTest;

/*
 * Data JPA Test for Role DAO layer, Run with Docker
 * If you will run test in another device, Change SQL scripts file path 
 * @Author VLadislav K
 */
@DataJpaTest
@AutoConfigureTestDatabase (replace =  AutoConfigureTestDatabase.Replace.NONE)
@Sql (scripts = {"file:C:\\Users\\Java\\git\\CourseSystem\\CourseSystem\\src\\test\\resources\\db\\clear.sql", "file:C:\\Users\\Java\\git\\CourseSystem\\CourseSystem\\src\\test\\resources\\db\\insert.sql"})
public class RoleDaoTest extends AbstractTest {
	
	@Autowired
	private RoleDao roleDao;
	
	@Test
	void testFindByName() {
		String roleName = "Admin";
		Role role = roleDao.findByroleName(roleName);
		assertEquals(roleName, role.getRoleName());
	}
	
	@Test
	void testFindNonExistingRole() {
		String roleName = "NewRole";
		Role role = roleDao.findByroleName(roleName);
		assertNull(role); 
	}
}
	


