package ats.managed_bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ats.model.Ranking;
import ats.service.RankingService;

@ManagedBean
@ViewScoped
public class RankingBean {
	@ManagedProperty(value = "#{rankingServiceImpl}")
	private RankingService rankingService;
	public List<Ranking> getRank_list() {
		return rank_list;
	}



	public void setRank_list(List<Ranking> rank_list) {
		this.rank_list = rank_list;
	}

	private List<Ranking> rank_list;
	@PostConstruct
	public void init() {
	rank_list=rankingService.listRanking();
	
	
	}
	
	
	
	public RankingService getRankingService() {
		return rankingService;
	}

	public void setRankingService(RankingService rankingService) {
		this.rankingService = rankingService;
	}
}
