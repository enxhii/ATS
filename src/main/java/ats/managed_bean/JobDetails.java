package ats.managed_bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ats.model.Job;

@ManagedBean
@SessionScoped
public class JobDetails {

	private Job job;

	final static Logger LOGGER = LogManager.getLogger(JobDetails.class);

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

}
