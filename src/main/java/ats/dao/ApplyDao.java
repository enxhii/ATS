package ats.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ats.model.CV;
import ats.model.Job;

@Repository
public class ApplyDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void uploadCv(CV file, Job job) {
		
		file.setJob(job);
		entityManager.persist(file);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
