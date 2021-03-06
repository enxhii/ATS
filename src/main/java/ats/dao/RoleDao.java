package ats.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import ats.model.Role;
import ats.model.User;

@Repository
public class RoleDao {

	@PersistenceContext
	private EntityManager entityManager;
	final static Logger LOGGER = LogManager.getLogger(RoleDao.class);
	private User user;
	private Role role;

	@SuppressWarnings("unchecked")
	public List<Role> listAll() {
		try {
			LOGGER.debug("Getting result from roles");
			String sql = "SELECT r FROM Role r";
			LOGGER.debug("Fetching result from roles");
			List<Role> list = entityManager.createQuery(sql).getResultList();
			return list;
		} catch (Exception e) {
			LOGGER.info(e);
			e.printStackTrace();
		}
		return null;

	}

	public Role getRoleById() {
		try {
			String query = "select r from Role r where r.name='Member'";
			Role role = entityManager.createQuery(query, Role.class).getSingleResult();
			return role;
		} catch (Exception e) {
			LOGGER.debug("RoleDao problem getROleById" + e);
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Role> getRolesA(int id) {
		try {
			String query = "select r.name from Role r join r.users u where u.id=?1";
			return getEntityManager().createQuery(query).setParameter(1, id).getResultList();
		} catch (Exception e) {
			LOGGER.debug(e);
		}
		return null;

	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
