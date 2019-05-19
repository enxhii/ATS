package ats.service;

import java.util.List;

import ats.model.Address;
import ats.model.Benefit;
import ats.model.Job;
import ats.model.JobCategory;
import ats.model.Qualifications;
import ats.model.Skill;
import ats.model.User;

public interface JobService {
	void addJob(Job entity, User user, Address address, List<Skill> skills, Qualifications qualifications,
			List<Benefit> benefits);

	public List<JobCategory> listJobCategory();

	public List<Benefit> listAll();

	public List<Job> jobList();
	public Job getJobById(Integer id) ;

}