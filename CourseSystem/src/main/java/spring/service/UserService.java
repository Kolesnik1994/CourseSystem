package spring.service;

import spring.entity.User;

public interface UserService {
	
	User loadUserByuserEmail (String userEmail);
	
	User createUser (String userEmail, String userPassword);
	
	void assignRoleToUser (String userEmail, String roleName);
	
	boolean doesUserHasRole(String roleName);
	
	

}
