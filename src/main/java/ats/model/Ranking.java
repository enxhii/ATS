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

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idjob")
	private Job job;

	public Job getJob() {
		return job;
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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name = "score")
	private String score;
}
