package ats.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import ats.model.CV;


@Repository
public class CVDao {
	@PersistenceContext
	private EntityManager entityManager;
	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	public CV getCvByName(String name) {
			try {
				String query = " select j from CV j where j.name=?1 ";
				return entityManager.createQuery(query, CV.class).setParameter(1, name).getSingleResult();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
}
