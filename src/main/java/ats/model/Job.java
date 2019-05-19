package ats.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "job")
@NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j")
public class Job implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idjob;

	@Column(name = "status")
	private int status;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "dateUpdated")
	@CreationTimestamp
	private Date dateUpdated;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;

	// bi-drectional many-to-one association to JobType
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "categoryId")
	private JobCategory jobCategory;

	@Column(name = "job_type")
	private String job_type;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "qualification_id", referencedColumnName = "idqualifications")
	private Qualifications qualifications;

	// bi-directional many-to-one association to Job
	@OneToMany(mappedBy = "job", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Skill> skills;
	// bi-directional many-to-one association to Job
	@OneToMany(mappedBy = "job", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CV> cv;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(name = "job_benefit", joinColumns = @JoinColumn(name = "id_job", referencedColumnName = "idjob"), inverseJoinColumns = @JoinColumn(name = "id_benefit", referencedColumnName = "id"))

	private List<Benefit> benefits;

	// bi-drectional many-to-one association to Address
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	public Job() {

	}

	public Integer getIdjob() {
		return idjob;
	}

	public void setIdjob(Integer idjob) {
		this.idjob = idjob;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JobCategory getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(JobCategory jobCategory) {
		this.jobCategory = jobCategory;
	}

	public String getJob_type() {
		return job_type;
	}

	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Qualifications getQualifications() {
		return qualifications;
	}

	public void setQualifications(Qualifications qualifications) {
		this.qualifications = qualifications;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public void setBenefits(List<Benefit> benefits) {
		this.benefits = benefits;
	}

	public List<CV> getCv() {
		return cv;
	}

	public void setCv(List<CV> cv) {
		this.cv = cv;
	}

	public List<Benefit> getBenefits() {
		return benefits;
	}

}
