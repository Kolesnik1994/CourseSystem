package spring.service.impl;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import spring.dao.RoleDao;
/**
 * Role Service Test
 * @author VLadislav K
 */

@ExtendWith (MockitoExtension.class)
class RoleServiceImplTest {
	
	@InjectMocks
	private RoleServiceIml roleServiceIml;
	
	@Mock
    private RoleDao roleDao;

	@Test
	void testLoadRolebyName() {
		roleServiceIml.loadRolebyName("Admin");
		verify(roleDao).findByroleName(any());	
	}
	

	@Test
	void testCreateRole() {
		roleServiceIml.createRole("Admin");
		verify(roleDao, times(1)).save(any());
	}
}
