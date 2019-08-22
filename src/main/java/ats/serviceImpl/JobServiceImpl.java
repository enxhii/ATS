package ats.serviceImpl;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ats.dao.JobDao;
import ats.model.Address;
import ats.model.Benefit;
import ats.model.Job;
import ats.model.JobCategory;
import ats.model.Qualifications;
import ats.model.Skill;
import ats.model.User;
import ats.service.JobService;
@Transactional
@Service
public class JobServiceImpl implements JobService {
	final static Logger LOGGER = LogManager.getLogger(JobServiceImpl.class);

	@Autowired
	private JobDao jobdao;

	@Override
	public void addJob(Job entity, User user ,Address address, Skill skills, Qualifications qualifications,
			List<Benefit> benefits) {
		try {
			jobdao.addJob(entity, user ,address, skills, qualifications, benefits);
			LOGGER.debug("JOBImpl successfully inserted ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JobDao getJobdao() {
		return jobdao;
	}

	public void setJobdao(JobDao jobdao) {
		this.jobdao = jobdao;
	}

	@Override
	public List<JobCategory> listJobCategory() {
		
		return jobdao.listJobCategory();
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	@Override
	public List<Benefit> listAll() {
		return jobdao.listAll();
	}

	@Override
	public List<Job> jobList() {
		return jobdao.jobList();
	}

	@Override
	public Job getJobById(Integer id) {
		// TODO Auto-generated method stub
		return jobdao.getJobById(id);
	}

}
