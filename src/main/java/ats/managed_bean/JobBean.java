package ats.managed_bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import ats.model.Address;
import ats.model.Benefit;
import ats.model.Job;
import ats.model.JobCategory;
import ats.model.Qualifications;
import ats.model.Skill;
import ats.model.User;
import ats.service.JobService;

@ManagedBean(name = "jobbean")
@RequestScoped
public class JobBean {
	@ManagedProperty(value = "#{jobServiceImpl}")
	private JobService jobService;

	@ManagedProperty(value = "#{userProfileBean}")
	private UserProfileBean userProfileBean;

	private Job job;
	private Address address;
	private List<Benefit> selectedBenefit;
	private List<Benefit> list;
	private Skill skill;
	private List<Skill> skills;
	private Qualifications qualifications;
	private Benefit benefit;
	private User user;
	private JobCategory jobcategory;
	private List<JobCategory> categoryList;
	private List<String> experienceLevel;
	private List<String> strengthLevel;
	private List<String> educationLevel;
	private List<String> jobtype;

	@PostConstruct
	public void init() {
		skill = new Skill();
		user = new User();
		job = new Job();
		address = new Address();
		qualifications = new Qualifications();
		selectedBenefit = jobService.listAll();
		list = jobService.listAll();
		skills = new ArrayList<Skill>();
		benefit = new Benefit();
		experienceLevel = new ArrayList<>();
		strengthLevel = new ArrayList<>();
		educationLevel = new ArrayList<>();
		jobtype = new ArrayList<>();
		categoryList = jobService.listJobCategory();
		experienceLevel.add("0 to 3 Years");
		experienceLevel.add("3 to 5 Years");
		experienceLevel.add("5 to 8 Years");
		experienceLevel.add("8 to 10 Years");
		experienceLevel.add("10 to 15 Years");
		experienceLevel.add("Greater than 15 Years");
		strengthLevel.add("Beginner");
		strengthLevel.add("Intermediate");
		strengthLevel.add("Advanced");
		strengthLevel.add("Expert");
		educationLevel.add("High School");
		educationLevel.add("Associate Degree");
		educationLevel.add("Bachelor Degree");
		educationLevel.add("Master Degree");
		educationLevel.add("Doctorate");
		educationLevel.add("No degree preferred ");
		jobtype.add("Full-Time");
		jobtype.add("Part-Time");
		jobtype.add("Contrat");
		jobtype.add("Contract-To-Hire");
		jobtype.add("Intership");
		jobtype.add("Volunteer");
		jobtype.add("Permanent");
		jobtype.add("Temporary");
		jobtype.add("Telecommute");

	}

	public void addJob() {
		user.setId(userProfileBean.getUser().getId());

		jobService.addJob(job, user, address, skills, qualifications, selectedBenefit);
		addMessage("Job " + "  " + job.getTitle() + "  " + " was publish  succesfully");
		job = new Job();
		address = new Address();
		skill=new Skill();
		skills = new ArrayList<>();
		qualifications = new Qualifications();
		selectedBenefit = new ArrayList<>();
	}

	public Benefit getBenefit(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Id not null");
		}
		Optional<Benefit> optional = list.stream().filter(benefit -> id.equals(benefit.getId())).findFirst();
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public JobCategory getCategory(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Id not null");
		}
		Optional<JobCategory> optional = categoryList.stream().filter(jobcategory -> id.equals(jobcategory.getId()))
				.findFirst();
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void onTabChange(TabChangeEvent event) {
		FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onTabClose(TabCloseEvent event) {
		FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Qualifications getQualifications() {
		return qualifications;
	}

	public void setQualifications(Qualifications qualifications) {
		this.qualifications = qualifications;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public Benefit getBenefit() {
		return benefit;
	}

	public void setBenefit(Benefit benefit) {
		this.benefit = benefit;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JobCategory getJobcategory() {
		return jobcategory;
	}

	public void setJobcategory(JobCategory jobcategory) {
		this.jobcategory = jobcategory;
	}

	public List<JobCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<JobCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public List<String> getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(List<String> experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public List<String> getStrengthLevel() {
		return strengthLevel;
	}

	public void setStrengthLevel(List<String> strengthLevel) {
		this.strengthLevel = strengthLevel;
	}

	public List<String> getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(List<String> educationLevel) {
		this.educationLevel = educationLevel;
	}

	public List<String> getJobtype() {
		return jobtype;
	}

	public void setJobtype(List<String> jobtype) {
		this.jobtype = jobtype;
	}

	public UserProfileBean getUserProfileBean() {
		return userProfileBean;
	}

	public void setUserProfileBean(UserProfileBean userProfileBean) {
		this.userProfileBean = userProfileBean;
	}

	public void setSelectedBenefit(List<Benefit> selectedBenefit) {
		this.selectedBenefit = selectedBenefit;
	}

	public void setList(List<Benefit> list) {
		this.list = list;
	}

	public List<Benefit> getSelectedBenefit() {
		return selectedBenefit;
	}

	public List<Benefit> getList() {
		return list;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		requestFacesContext().addMessage(null, message);
	}

	private FacesContext requestFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	@SuppressWarnings("unused")
	private RequestContext requestContext() {
		return RequestContext.getCurrentInstance();
	}

}
