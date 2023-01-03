package spring.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.RoleDao;
import spring.dao.UserDao;
import spring.entity.Role;
import spring.entity.User;
import spring.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	
	private RoleDao roleDao;

	public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	@Override
	public User loadUserByuserEmail(String userEmail) {
		return userDao.findByuserEmail(userEmail);
	}

	@Override
	public User createUser(String userEmail, String userPassword) {
		return userDao.save(new User (userEmail, userPassword));
	}

	@Override
	public void assignRoleToUser(String userEmail, String roleName) {
		
		User user = loadUserByuserEmail (userEmail);
		Role role = roleDao.findByroleName(roleName);
		user.assignRoleToUser(role);
		
		
	}

}
