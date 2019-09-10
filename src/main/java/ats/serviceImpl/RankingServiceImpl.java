package ats.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ats.dao.RankingDao;
import ats.model.CV;
import ats.model.Job;
import ats.model.Ranking;
import ats.service.RankingService;
@Transactional
@Service
public class RankingServiceImpl implements RankingService {
@Autowired
private RankingDao rankingDao;
	public RankingDao getRankingDao() {
	return rankingDao;
}

public void setRankingDao(RankingDao rankingDao) {
	this.rankingDao = rankingDao;
}

	@Override
	public void addRanking(Job job, CV cv, double score) {
rankingDao.addRanking(job, cv, score);
	}

	@Override
	public List<Ranking> listRanking() {		
		
		return rankingDao.listRanking();
	}

	@Override
	public Ranking getRankingByJobId(Integer id) {
		return rankingDao.getRankingByJobId(id);
	}

	@Override
	public Ranking getRankingByCVId(Integer id) {
		return rankingDao.getRankingByCVId(id);
	}

}
