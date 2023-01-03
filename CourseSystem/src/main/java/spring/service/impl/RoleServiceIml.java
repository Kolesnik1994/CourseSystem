package spring.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.RoleDao;
import spring.entity.Role;
import spring.service.RoleService;

@Service
@Transactional
public class RoleServiceIml implements RoleService {
	
	private RoleDao roleDao;
	public RoleServiceIml(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public Role loadRolebyName(String roleName) {
		return roleDao.findByroleName(roleName);
	}

	
	@Override
	public Role createRole(String roleName) {
		return roleDao.save(new Role(roleName));
	}

}
