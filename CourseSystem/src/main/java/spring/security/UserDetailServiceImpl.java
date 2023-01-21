package spring.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring.entity.User;
import spring.service.UserService;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	private UserService userService;
	public UserDetailServiceImpl(UserService userService) {
		this.userService = userService;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	   
		User user = userService.loadUserByuserEmail(email);
	    if (user == null) throw new UsernameNotFoundException ("User not found");
	    
	    Collection <GrantedAuthority> authorities = new ArrayList<>();
	    user.getRoles().forEach(role-> {SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
	                                   authorities.add(authority);
	    });
	    org.springframework.security.core.userdetails.User userDetail = new  org.springframework.security.core.userdetails.User(user.getUserEmail(), 
	    		user.getUserPassword(), authorities);
		return userDetail;
	
}
	}