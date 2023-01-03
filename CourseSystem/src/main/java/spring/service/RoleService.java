package spring.service;

import spring.entity.Role;

public interface RoleService {
	
	Role loadRolebyName(String roleName);
	
	Role createRole (String roleName);
	
	

}
