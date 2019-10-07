package ats.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import ats.model.Address;
import ats.model.Benefit;
import ats.model.Job;
import ats.model.JobCategory;
import ats.model.Qualifications;
import ats.model.Skill;
import ats.model.User;

@Repository
public class JobDao {
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	final static Logger LOGGER = LogManager.getLogger(JobDao.class);

	public void addJob(Job job, User user, Address address, Skill skills, Qualifications qualifications,
			List<Benefit> benefits) {
		try {
			job.setUser(user);
			LOGGER.info("User Inserted");
			job.setAddress(address);
			LOGGER.info("Address Inserted");
			job.setSkills(skills);
			LOGGER.info("SkillInserted");
			job.setQualifications(qualifications);
			LOGGER.info("Qualifications Inserted");
			job.setBenefits(benefits);
			LOGGER.info("Benefits Inserted");
			entityManager.persist(job);
			LOGGER.info("Job Inserted");

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug(e + "JobDao class problem ");
		}
	}

	public List<JobCategory> listJobCategory() {
		try {
			LOGGER.debug("Getting result from Job Category");
			String sql = "SELECT j FROM JobCategory  j";
			LOGGER.debug("Fetching result from JobCategory");
			@SuppressWarnings("unchecked")
			List<JobCategory> jobCategoryts = entityManager.createQuery(sql).getResultList();
			return jobCategoryts;
		} catch (Exception e) {
			LOGGER.info(e);
			e.printStackTrace();
		}
		return null;

	}

	public List<Job> jobList() {
		try {
			LOGGER.debug("Getting result from Job ");
			String sql = "SELECT j FROM Job  j";
			LOGGER.debug("Fetching result from Jobs");
			@SuppressWarnings("unchecked")
			List<Job> jobs = entityManager.createQuery(sql).getResultList();
			return jobs;
		} catch (Exception e) {
			LOGGER.info("Job Dao Class problem");
			
		}
		return null;
	}

	public Job getJobById(Integer id) {
		try {
			String query = " select j from Job j  where j.idjob=?1 ";
			return entityManager.createQuery(query, Job.class).setParameter(1, id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Benefit> listAll() {
		try {
			LOGGER.debug("Getting result from benefit");
			String sql = "SELECT b FROM Benefit b";
			LOGGER.debug("Fetching result from benefits");
			@SuppressWarnings("unchecked")
			List<Benefit> list = entityManager.createQuery(sql).getResultList();
			return list;
		} catch (Exception e) {
			LOGGER.info(e);
			e.printStackTrace();
		}
		return null;

	}

	public static Logger getLogger() {
		return LOGGER;
	}

}
