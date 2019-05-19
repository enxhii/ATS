package ats.managed_bean;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ats.model.Role;
import ats.model.User;
import ats.service.RoleService;
import ats.service.UserService;

@ManagedBean(name = "login")
@RequestScoped
public class LoginManagedBean {
	private String username;
	private String password;
	@ManagedProperty(value = "#{roleServiceImpl}")
	private RoleService roleService;
	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;

	@ManagedProperty(value = "#{userProfileBean}")
	private UserProfileBean userProfileBean;
	private User user;
	private Role role;
	private List<User> userlist;
	private List<Role> listRole;
	final static Logger LOGGER = LogManager.getLogger(LoginManagedBean.class);
	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

	public String submit() {
		try {
			LOGGER.debug(userService.getUser(username, password));
			System.out.println("(userService.getUser(username, password)");
			if (userService.doesExists(username)) {
				user = userService.getUser(username, password);
				userProfileBean.setUser(user);
				LOGGER.debug(user.getUsername());
				LOGGER.debug(userService.doesExists(username));

					LOGGER.debug("User with username " + user.getUsername() + "logged in");
LOGGER.debug(user.getRoli().isEmpty());
					return "success";
			
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "failure";
	}

	public String logout() throws IOException {
		user = userProfileBean.getUser();
		user = null;
		externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
		return "logout";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserProfileBean getUserProfileBean() {
		return userProfileBean;
	}

	public void setUserProfileBean(UserProfileBean userProfileBean) {
		this.userProfileBean = userProfileBean;
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

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}

	public List<Role> getListRole() {
		return listRole;
	}

	public void setListRole(List<Role> listRole) {
		this.listRole = listRole;
	}
}
