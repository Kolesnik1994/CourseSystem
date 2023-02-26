package spring.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import spring.CourseSystemApplication;
import spring.entity.User;
import spring.service.impl.AbstractTest;

/*
 * Data JPA Test for User DAO layer, Run with Docker
 * If you will run test in another device, Change SQL scripts file path 
 * @Author VLadislav K
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = { "file:C:\\Users\\Java\\git\\CourseSystem\\CourseSystem\\src\\test\\resources\\db\\clear.sql",
		         "file:C:\\Users\\Java\\git\\CourseSystem\\CourseSystem\\src\\test\\resources\\db\\insert.sql" })
public class UserDaoTest extends AbstractTest {

	@Autowired
	private UserDao userDao;

	@Test
	void testFindByEmai() {
		User user = userDao.findByuserEmail("egor@gmail.com");
		Long expectedId = 3L;

		assertEquals(expectedId, user.getUserId());
	}

	@Test
	void testFindUserNonExistingEmail() {
		User user = userDao.findByuserEmail("NonExistEmail@mail.com");
		assertNull(user);
	}

}
