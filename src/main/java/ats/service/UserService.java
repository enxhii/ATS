package ats.service;

import java.util.List;

import ats.model.*;

public interface UserService {
	public User getUser(String username, String password);

	public boolean doesExists(String username);

	public void save(User entity,  List<Role> role);

	public void delete(Integer id);

	public void enableUsers(Integer id);


	public List<User> getDisabledUser();

	public List<User> listAll();

	public void updatePassword(User user, String password);

	public void updateProfile(User user);

	public void updateUsers(User user,  List<Role> roles);
}

