package ats.serviceImpl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ats.dao.RoleDao;
import ats.model.Role;
import ats.service.RoleService;

@Transactional
@Service

public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roledao;
	final static Logger LOGGER = LogManager.getLogger(RoleServiceImpl.class);

	@Override
	public List<Role> listRoles() {
		try {
			return roledao.listAll();
		} catch (Exception e) {
			LOGGER.debug("No results");
			LOGGER.info(e.getMessage());
		}
		return null;
	}

	@Override
	public Role getRoleById(int id) {
		return roledao.getRoleById();
	}

	public RoleDao getRoledao() {
		return roledao;
	}

	public void setRoledao(RoleDao roledao) {
		this.roledao = roledao;
	}

	@Override
	public List<Role> getRolesA(int id) {
		try {
			LOGGER.debug(roledao.getRolesA(id));
			return roledao.getRolesA(id);

		} catch (Exception e) {
			LOGGER.debug(e);
			return null;
		}

	}
}
