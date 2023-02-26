package spring.service;

import spring.entity.User;

/*
 * User Interface that can used for different purpose (testing, service layer and other)
 * @Author VLadislav K
 */
public interface UserService {
	
	User loadUserByuserEmail (String userEmail);
	
	User createUser (String userEmail, String userPassword);
	
	void assignRoleToUser (String userEmail, String roleName);
	
	boolean doesUserHasRole(String roleName);
	
	

}
