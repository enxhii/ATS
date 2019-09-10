package ats.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import ats.model.CV;
import ats.model.Job;
import ats.model.Ranking;


@Repository
public class RankingDao {
	@PersistenceContext
	private EntityManager entityManager;
	final static Logger LOGGER = LogManager.getLogger(JobDao.class);

	public void addRanking(Job job,CV cv,double score) {
		try {
			Ranking ranking = new Ranking();
			ranking.setJob(job);
			ranking.setScore(score);
			ranking.setCv(cv);
			entityManager.persist(ranking);
			LOGGER.info("Ranking Inserted");

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug(e + "RankingDao class problem ");
		}
	}

	public List<Ranking> listRanking() {
		try {
			LOGGER.debug("Getting result from Ranking ");
			String sql = "SELECT r FROM ranking  r";
			LOGGER.debug("Fetching result from Ranking");
			@SuppressWarnings("unchecked")
			List<Ranking> jobCategoryts = entityManager.createQuery(sql).getResultList();
			return jobCategoryts;
		} catch (Exception e) {
			LOGGER.info(e);
			e.printStackTrace();
		}
		return null;

	}

	public Ranking getRankingByJobId(Integer id) {
		try {
			String query = " select r from ranking r  where r.idjob=?1 ";
			LOGGER.debug(entityManager.createQuery(query, Ranking.class).setParameter(1, id).getSingleResult());
			return entityManager.createQuery(query, Ranking.class).setParameter(1, id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Ranking getRankingByCVId(Integer id) {
		try {
			String query = " select r from ranking r  where r.idcv=?1 ";
			LOGGER.debug(entityManager.createQuery(query, Ranking.class).setParameter(1, id).getSingleResult());
			return entityManager.createQuery(query, Ranking.class).setParameter(1, id).getSingleResult();
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

	public static Logger getLogger() {
		return LOGGER;
	}

}
