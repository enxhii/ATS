package ats.service;

import java.util.List;

import ats.model.CV;
import ats.model.Job;
import ats.model.Ranking;

public interface RankingService {
	public void addRanking(Job job,CV cv,double score) ;
	public List<Ranking> listRanking() ;
	public Ranking getRankingByJobId(Integer id) ;
	public Ranking getRankingByCVId(Integer id) ;

}
