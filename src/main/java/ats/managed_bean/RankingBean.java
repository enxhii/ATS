package ats.managed_bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import ats.model.CV;
import ats.model.Job;
import ats.model.Ranking;
import ats.service.RankingService;

@ManagedBean
@ViewScoped
public class RankingBean {
	@ManagedProperty(value = "#{rankingServiceImpl}")
	private RankingService rankingService;
	private Ranking ranking;
	private Job job;
	private CV cv;
	private List<Ranking> rank_list;

	@PostConstruct
	public void init() {
		rank_list = rankingService.listRanking();
		ranking = new Ranking();
		job = new Job();
		cv = new CV();

	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public CV getCv() {
		return cv;
	}

	public void setCv(CV cv) {
		this.cv = cv;
	}

	public Ranking getRanking() {
		return ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	public RankingService getRankingService() {
		return rankingService;
	}

	public void setRankingService(RankingService rankingService) {
		this.rankingService = rankingService;
	}

	public List<Ranking> getRank_list() {
		return rank_list;
	}

	public void setRank_list(List<Ranking> rank_list) {
		this.rank_list = rank_list;
	}
}
