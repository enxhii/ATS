package ats.managed_bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import ats.model.Benefit;
import ats.model.CV;
import ats.model.Job;
import ats.model.Qualifications;
import ats.model.Skill;
import ats.service.CvParserService;
import ats.service.CvService;
import ats.service.JobService;

@ManagedBean
@ViewScoped
public class ApplyBean {

	@ManagedProperty(value = "#{jobServiceImpl}")
	private JobService jobService;

	@ManagedProperty(value = "#{cvServiceImpl}")
	private CvService cvService;

	@ManagedProperty(value="#{cvParserImplService}")
	private CvParserService cv_service;

	private List<Job> jobs;
	private Job job;
	final static Logger LOGGER = LogManager.getLogger(ApplyBean.class);
	private CV file;
	private Integer idjob;
	private UploadedFile upload;
	private Benefit benefit;
	private Skill skill;
	private Qualifications qualifications;


	private static final String PF_ADDFILE_DIALOG_HIDE = "PF('AddFileDialog').hide()";
	private static final String PF_ADDFILE_DIALOG_SHOW = "PF('AddFileDialog').show()";

	@PostConstruct
	public void init() {
		jobs = jobService.jobList();
		job = new Job();
		job = jobService.getJobById(idjob);
		file = new CV();
	}

	/*
	 * public void apply(FileUploadEvent event) throws Exception {
	 * file.setName(event.getFile().getFileName());
	 * file.setData(event.getFile().getContents()); Path folder =
	 * Paths.get("C:/Users/pc/eclipse-workspace/ATS/resumes"); String filename =
	 * FilenameUtils.getBaseName(event.getFile().getFileName()); String extension =
	 * FilenameUtils.getExtension(event.getFile().getFileName()); Path fileToFolder
	 * = Files.createTempFile(folder, filename + "-", "." + extension); try
	 * (InputStream input = event.getFile().getInputstream()) { Files.copy(input,
	 * fileToFolder, StandardCopyOption.REPLACE_EXISTING); } cvService.addCv(file,
	 * job); cv_service.extractTextFromPdf(job.getIdjob(),file.getName());
	 * addMessage("You have succesfully applied for the position " +
	 * job.getTitle()); executeScript(PF_ADDFILE_DIALOG_HIDE);
	 * 
	 * 
	 * }
	 */
	public void apply(FileUploadEvent event) throws IOException {
	
		file.setName(event.getFile().getFileName());
		file.setData(event.getFile().getContents());
		
		String filename = FilenameUtils.getBaseName(event.getFile().getFileName());
		String extension = FilenameUtils.getExtension(event.getFile().getFileName());
		Path folder = Paths.get("C:/Users/pc/eclipse-workspace/ATS/resumes/" + filename + "." + extension);
		Path fileToFolder = Files.createFile(folder); //shif, ti ben create file edhe i kalon pathin e foldeit :P car ti kaloj
			
		try (InputStream input = event.getFile().getInputstream()) {
			Files.copy(input, fileToFolder, StandardCopyOption.REPLACE_EXISTING);
		}
		
		cvService.addCv(file, job); // po ky job nga vjen nga front /job whste ok
		
		try {
			cv_service.extractTextFromPdf(job.getIdjob(), file.getName());
			//cv_service.print();
			
			System.out.println("job id" + job.getIdjob() + file.getName());// as ket
		} catch (Exception e) {

			e.printStackTrace();
		}
		addMessage("You have succesfully applied for the position " + job.getTitle());
		executeScript(PF_ADDFILE_DIALOG_HIDE);

	}

	public CvParserService getCv_service() {
		return cv_service;
	}

	public void setCv_service(CvParserService cv_service) {
		this.cv_service = cv_service;
	}

	public void onLinkClick() {
		executeScript(PF_ADDFILE_DIALOG_SHOW);
	}

	public UploadedFile getUpload() {
		return upload;
	}

	public void setUpload(UploadedFile upload) {
		this.upload = upload;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		requestFacesContext().addMessage(null, message);
	}

	private FacesContext requestFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	@SuppressWarnings("unused")
	private RequestContext requestContext() {
		return RequestContext.getCurrentInstance();
	}

	public void setFile(CV file) {
		this.file = file;
	}

	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public CvService getCvService() {
		return cvService;
	}

	public void setCvService(CvService cvService) {
		this.cvService = cvService;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public CV getFile() {
		return file;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Qualifications getQualifications() {
		return qualifications;
	}

	public void setQualifications(Qualifications qualifications) {
		this.qualifications = qualifications;
	}

	public Benefit getBenefit() {
		return benefit;
	}

	public void setBenefit(Benefit benefit) {
		this.benefit = benefit;
	}

	public Integer getIdjob() {
		return idjob;
	}

	public void setIdjob(Integer idjob) {
		this.idjob = idjob;
	}

	private void executeScript(String script) {
		requestContext().execute(script);
	}
}
