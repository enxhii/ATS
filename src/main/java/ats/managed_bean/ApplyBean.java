package ats.managed_bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ats.model.CV;
import ats.model.Job;
import ats.service.CvService;
import ats.service.JobService;

@ManagedBean
@ViewScoped
public class ApplyBean {

	@ManagedProperty(value = "#{jobServiceImpl}")
	private JobService jobService;
	@ManagedProperty(value = "#{jobDetails}")
	private JobDetails jobDetails;
	@ManagedProperty(value = "#{cvServiceImpl}")
	private CvService cvService;
	private List<Job> jobs;
	private Job job;
	final static Logger LOGGER = LogManager.getLogger(ApplyBean.class);

	private CV file;

	@PostConstruct
	public void init() {
		jobs = jobService.jobList();
		job = new Job();
//		job = jobService.getJobById(jobDetails.getJob().getIdjob()); ->Kjo me jep null //edhekur eprovoj te Job details me scope Session

	}

	public void apply() {
		job = jobService.getJobById(jobDetails.getJob().getIdjob());
		cvService.addCv(file, job);
	}

	public void getId() {
		LOGGER.debug(job.getIdjob());

	}

	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public JobDetails getJobDetails() {
		return jobDetails;
	}

	public void setJobDetails(JobDetails jobDetails) {
		this.jobDetails = jobDetails;
	}

	public CvService getCvService() {
		return cvService;
	}

	public void setCvService(CvService cvService) {
		this.cvService = cvService;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public CV getFile() {
		return file;
	}

	public void setFile(CV file) {
		this.file = file;
	}

}
