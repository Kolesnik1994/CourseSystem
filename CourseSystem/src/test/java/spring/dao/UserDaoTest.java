package spring.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import spring.entity.User;
import spring.service.impl.AbstractTest;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql (scripts = {"C:\\Users\\Java\\eclipse-workspace\\CourseSystem\\db\\clear.sql", "C:\\Users\\Java\\eclipse-workspace\\CourseSystem\\db\\insert.sql"})
public class UserDaoTest extends AbstractTest {
	
	@Autowired
	private UserDao userDao;
	
	
	@Test
	void testFindByEmai () {
	User user =userDao.findByuserEmail("user@gmail.com");
	Long expectedId =4L;
	
	assertEquals(expectedId, user.getUserId());
	
	}
	
	@Test
	void testFindUserNonExistingEmail () {
		
		User user = userDao.findByuserEmail("nonexistinf@gmail.con");
		assertNull(user);
	
	}

}
