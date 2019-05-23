package ats.managed_bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ats.model.Job;

@ManagedBean
@ViewScoped
public class JobDetails {

	@ManagedProperty(value = "#{applyBean}")
	private ApplyBean applyBean;
	private Job job;

	@PostConstruct
	public void init() {
		//job = new Job();
		//job.setIdjob(applyBean.getId());
		//LOGGER.debug(job.getIdjob());
	}

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

	public ApplyBean getApplyBean() {
		return applyBean;
	}

	public void setApplyBean(ApplyBean applyBean) {
		this.applyBean = applyBean;
	}

}
