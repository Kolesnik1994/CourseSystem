package spring.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import spring.dao.RoleDao;
import spring.dao.UserDao;
import spring.entity.Role;
import spring.entity.User;

/**
 * Testing User Service layer
 * @author VLadislav K
 */
@ExtendWith (MockitoExtension.class)
class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl userService;
   
  @Mock
  private UserDao userDao;
  
  @Mock
  private User mockUser;
  
  @Mock
  private RoleDao roleDao;
	
  /**
   * load user by Email
   * testing that Mock userDao one time find user by Email -> method findByuserEmail with any parameter;
   */
	@Test
	void testLoadUserByuserEmail() {
		userService.loadUserByuserEmail("user@gmail.com");
		verify(userDao, times(1)).findByuserEmail(any());
	}

	
	@Test
	void testAssignRoleToUser() {
		Role role = new Role();
		role.setRoleId(1L);
		
		when(userDao.findByuserEmail(any())).thenReturn(mockUser);
		when(roleDao.findByroleName(any())).thenReturn(role);
		
		userService.assignRoleToUser("email@gmail.com", "pass");
		verify(mockUser, times(1)).assignRoleToUser(role);
		
	}

}





