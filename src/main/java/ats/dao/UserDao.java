package ats.dao;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import ats.encryption.PasswordEncryptor;
import ats.model.Role;
import ats.model.User;

@Repository
public class UserDao {
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)

	private EntityManager entityManager;
	
	final static Logger LOGGER = LogManager.getLogger(UserDao.class);
	private static final int VALID = 1;
	private static final int INVALID = 0;

	private RoleDao roleDao;
	ats.encryption.PasswordEncryptor passwordEncryptor = new ats.encryption.PasswordEncryptor();

	public User getUser(String username, String password) {
		try {
			String query1 = " select u  from User u  where u.username=:username and u.password=:password and u.status=:valid";
			User listuser = entityManager.createQuery(query1, User.class).setParameter("username", username)
					.setParameter("password", PasswordEncryptor.encrypt(password)).setParameter("valid", VALID)
					.getSingleResult();
			LOGGER.debug(listuser);
			System.out.println(listuser);
			return listuser;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug(e);
			LOGGER.debug("Problem from UserDao class");
			System.out.println("e.printStackTrace()");
			System.out.println(e);
			return null;
		}
	}

	public boolean doesExists(String username) {
		String query = " select u.username from User u where u.username=?1";
		try {
			System.out.println(query);
			return getEntityManager().createQuery(query).setParameter(1, username).getResultList().stream().findFirst()
					.isPresent();
			
		} catch (Exception e) {
			LOGGER.debug(e);
			System.out.println(e);
			return false;
		}
	}

	public User getUserById(Integer id) {
		String query = "select u.id from User u where u.id=?1";
		return entityManager.createQuery(query, User.class).setParameter(1, id).getSingleResult();
	}

	public void addUser(User user, List<Role> roles) {
		try {

			user.setRoli(roles);
			user.setStatus(VALID);

			user.setPassword(PasswordEncryptor.encrypt(user.getPassword()));
			entityManager.persist(user);
			LOGGER.info("Customer Inserted");
			LOGGER.info("User succesfully registered");

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug(e + "AddUser dao class problem ");
		}
	}

	public List<User> listAll() {
		try {
			LOGGER.info("Getting result from user");
			String sql = "select u from User u where u.status=?1";
			List<User> lista = entityManager.createQuery(sql, User.class).setParameter(1, VALID).getResultList();
			LOGGER.info("Fetching result from user");
			return lista;
		} catch (Exception exception) {
			LOGGER.debug(exception);
			exception.printStackTrace();
		}
		return Collections.emptyList();

	}

	public void deleteUser(Integer id) {
		try {
			String query = "update User set status=?1 where id=?2";
			Query query2 = entityManager.createQuery(query).setParameter(1, INVALID).setParameter(2, id);
			query2.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
		}
	}

	public void updateUser(User user, List<Role> roles) {
		try {
			roles = roleDao.listAll();

			LOGGER.debug("Roles updated");
			user.setRoli(roles);
			entityManager.merge(user);
			LOGGER.debug("User succesfully  updated");
		} catch (Exception e) {
			LOGGER.debug(e);
		}
	}

	public void updateProfile(User user) throws Exception {
		try {
			;
			entityManager.merge(user);
			LOGGER.debug("User successfully updated ");
		} catch (Exception e) {
			LOGGER.debug(e);
		}
	}

	public List<User> getDisabledUser() {
		try {
			LOGGER.info("Getting result from disabled user");
			String sql = "select u from User u where u.status=?1";
			List<User> lista = entityManager.createQuery(sql, User.class).setParameter(1, INVALID).getResultList();
			LOGGER.info("Fetching result from user");
			return lista;
		} catch (Exception exception) {
			LOGGER.debug(exception);
			exception.printStackTrace();
		}
		return Collections.emptyList();
	}

	public void enableUsers(Integer id) {
		String query = "update User set status=:status where id=:id";
		Query query2 = entityManager.createQuery(query).setParameter("status", VALID).setParameter("id", id);
		query2.executeUpdate();
	}

	public void updatePassword(User user, String password) {
		try {
			entityManager.refresh(user);
			LOGGER.debug("Setting new password");
			LOGGER.debug("Non encypted password" + password);
			LOGGER.debug("Encrypted password" + PasswordEncryptor.encrypt(password));
			user.setPassword(PasswordEncryptor.encrypt(password));
			LOGGER.debug(PasswordEncryptor.encrypt(password));
			entityManager.merge(user);
			LOGGER.debug("Password succesfully updated ");
		} catch (Exception e) {
			LOGGER.debug("Error from UserDao class" + e);
		}
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

}
