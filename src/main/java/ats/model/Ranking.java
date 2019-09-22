package ats.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ranking")
@NamedQuery(name = "Ranking.findAll", query = "SELECT r FROM Ranking r")
public class Ranking implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idranking;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "idjob")
	private Job job;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idcv")
	private CV cv;

	public Job getJob() {
		return job;
	}

	public CV getCv() {
		return cv;
	}

	public void setCv(CV cv) {
		this.cv = cv;
	}

	public Integer getIdranking() {
		return idranking;
	}

	public void setIdranking(Integer idranking) {
		this.idranking = idranking;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name = "score")
	private double score;
	
	@Column(name = "match_skill")
	private String  match_skill;

	public String getMatch_skill() {
		return match_skill;
	}

	public void setMatch_skill(String match_skill) {
		this.match_skill = match_skill;
	}

}
