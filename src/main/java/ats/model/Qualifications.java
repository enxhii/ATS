package ats.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "qualifications")
@NamedQuery(name = "Qualifications.findAll", query = "SELECT q FROM Qualifications q")

public class Qualifications implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idqualifications", unique=true, nullable=false)
	private int id;
	@Column(name = "educationLevel")
	private String educationLevel;
	
	@Column(name = "specialization")
	private String specialization;
	
	@OneToMany(mappedBy = "qualifications", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Job> job;

	public Qualifications() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setJob(List<Job> job) {
		this.job = job;
	}

}
