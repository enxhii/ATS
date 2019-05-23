package ats.managed_bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
		job = jobService.getJobById(Integer
				.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idjob")));
		// job.setIdjob(Integer
		// .valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("jobid")));

		LOGGER.debug(job.getIdjob() + "Is this null,why?Please compile and run andgoand fucking fucking gooo");
		file = new CV();
	}

	public String getId() {
		FacesContext fContext = FacesContext.getCurrentInstance();

		Map<String, String> params = fContext.getExternalContext().getRequestParameterMap();
		LOGGER.debug(params.get("idjob"));
		String id = params.get("idjob");
		LOGGER.debug(id);
		return id;
	}

	public void apply() {
		job.setIdjob((Integer.valueOf(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idjob"))));
		LOGGER.debug(job.getIdjob() + "Is this null,why?Please compile and run andgoand fucking fucking gooo");

		LOGGER.debug(job.getIdjob());
		cvService.addCv(file, job);
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

}
