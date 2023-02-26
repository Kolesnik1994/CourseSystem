package spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.entity.User;

/**
 * User DAO layer
 * @author VLadislav K
 */
public interface UserDao extends JpaRepository <User, Long> {
	
	User findByuserEmail (String userEmail);

}
