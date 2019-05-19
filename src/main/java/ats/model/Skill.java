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
import javax.persistence.Table;

@Entity
@Table(name = "skill")

public class Skill implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idskill", unique=true, nullable=false)

	private int id;
	@Column(name = "name")
	private String name;

	@Column(name = "experienceLevel")

	private String experienceLevel;

	@Column(name = "strengthLevel")

	private String strengthLevel;
	// bi-drectional many-to-one association to Address
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "job_id")
	private Job job;

	public Skill() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public String getStrengthLevel() {
		return strengthLevel;
	}

	public void setStrenghtLevel(String strengthLevel) {
		this.strengthLevel = strengthLevel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setStrengthLevel(String strengthLevel) {
		this.strengthLevel = strengthLevel;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
}
