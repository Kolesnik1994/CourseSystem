package spring.service;

import spring.entity.Role;

/*
 * Role Interface that can used for different purpose (testing, service layer and other)
 * @Author VLadislav K
 */
public interface RoleService {
	
	Role loadRolebyName(String roleName);
	
	Role createRole (String roleName);
	
	

}
