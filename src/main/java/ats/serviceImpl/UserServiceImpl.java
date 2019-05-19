package ats.serviceImpl;

import java.util.Collections;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ats.dao.RoleDao;
import ats.dao.UserDao;
import ats.model.Role;
import ats.model.User;
import ats.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userdao;
	@Autowired
	private RoleDao roleDao;
	final static Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
	private List<User> users;

	@Override
	public boolean doesExists(String username) {
		if (userdao.doesExists(username)) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> listAll() {
		try {
			LOGGER.info("Result Found");
			users = userdao.listAll();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage() + "Error catched in ServiceImpl");
		}
		return Collections.emptyList();

	}

	@Override
	public void save(User entity, List<Role> role) {
		try {
			userdao.addUser(entity,  role);
			LOGGER.info("User succesfully added");
		} catch (Exception e) {
			LOGGER.info(e);
			LOGGER.info("Something went wrong" + e);
		}
	}

	@Override
	public void delete(Integer id) {
		userdao.deleteUser(id);
	}

	@Override
	public void updatePassword(User user, String password) {
		try {
			LOGGER.debug("Updating password");
			userdao.updatePassword(user, password);
			LOGGER.debug("Password updated ");

		} catch (Exception e) {
			LOGGER.debug("An error happened " + e);
		}

	}

	@Override
	public void updateProfile(User user) {
		try {
			userdao.updateProfile(user);
		} catch (Exception e) {
			LOGGER.debug(e);
		}
	}

	@Override
	public void updateUsers(User user,  List<Role> roles) {
		try {
			
			userdao.updateUser(user,  roles);
		} catch (Exception e) {
			LOGGER.debug(e);
		}
	}

	@Override
	public void enableUsers(Integer id) {
		userdao.enableUsers(id);

	}

	@Override
	public List<User> getDisabledUser() {

		return userdao.getDisabledUser();
	}

	@Override
	public User getUser(String username, String password) {
		return userdao.getUser(username, password);
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public UserDao getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


		
	}
