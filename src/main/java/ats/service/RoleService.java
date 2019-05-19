package ats.service;

import java.util.List;

import ats.model.Role;

public interface RoleService {
	public List<Role> listRoles();

	public Role getRoleById(int id);

	public List<Role> getRolesA(int id);
}
