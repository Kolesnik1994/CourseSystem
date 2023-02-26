package spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.entity.Role;

/**
 * Role DAO layer
 * @author VLadislav K
 */
public interface RoleDao extends JpaRepository <Role, Long> {
	
	Role findByroleName (String keyword);
	
	

}
