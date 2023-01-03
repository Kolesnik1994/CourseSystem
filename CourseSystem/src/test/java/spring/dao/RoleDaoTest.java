package spring.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import spring.entity.Role;
import spring.service.impl.AbstractTest;

@DataJpaTest
@AutoConfigureTestDatabase (replace =  AutoConfigureTestDatabase.Replace.NONE)
@Sql (scripts = {"C:\\Users\\Java\\eclipse-workspace\\CourseSystem\\db\\clear.sql", "C:\\Users\\Java\\eclipse-workspace\\CourseSystem\\db\\insert.sql"})

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
	


