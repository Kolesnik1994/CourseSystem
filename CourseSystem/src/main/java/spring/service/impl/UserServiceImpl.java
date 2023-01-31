package spring.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	private PasswordEncoder	passwordEncoder;

	public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User loadUserByuserEmail(String userEmail) {
		return userDao.findByuserEmail(userEmail);
	}

	@Override
	public User createUser(String userEmail, String userPassword) {
		User user = loadUserByuserEmail (userEmail);
		if (user != null) throw new RuntimeException("User with email :" + userEmail + "already exist") ;
		String encode = passwordEncoder.encode(userPassword);
		return userDao.save(new User (userEmail, encode));
	}

	@Override
	public void assignRoleToUser(String userEmail, String roleName) {
		User user = loadUserByuserEmail (userEmail);
		Role role = roleDao.findByroleName(roleName);
		user.assignRoleToUser(role);
		 
	}

	@Override
	public boolean doesUserHasRole(String roleName) {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
	}
}
