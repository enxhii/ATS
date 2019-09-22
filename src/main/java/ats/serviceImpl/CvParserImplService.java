package ats.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import ats.dao.CVDao;
import ats.dao.JobDao;
import ats.dao.RankingDao;
import ats.model.CV;
import ats.model.CVInformation;
import ats.model.ExperienceModel;
import ats.model.Job;
import ats.service.CvParserService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

@Transactional
@Service
public class CvParserImplService implements CvParserService {

	@Autowired
	private JobDao jobRepo;

	@Autowired
	private RankingDao rankRepo;

	@Autowired
	private CVDao cvDao;
	final static Logger LOGGER = LogManager.getLogger(CvParserImplService.class);

	public void extractTextFromPdf(int jobID, String cv_name) throws Exception {

		LOGGER.debug("JOB ID  ..." + jobID);
		LOGGER.debug("CV NAME ..." + cv_name);

		String serverUrl = "http://localhost:3000/"; // this is where we are sending the request

		System.out.println("Before call ...");
		//OkHttpClient client = new OkHttpClient();
		//this was added now just for sure 
		OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS).build();
		RequestBody formBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("cv", "cv",
				RequestBody.create(MediaType.parse("application/pdf"), getCVFromResources(cv_name))).build();
		Request request = new Request.Builder().url(serverUrl).post(formBody).build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			System.out.println(response = client.newCall(request).execute());// TODO: handle exception

		} catch (Exception e) {
			System.out.println("ERR REQUEST");// TODO: handle exception
		}

		System.out.println("CV PATH : " + getCVFromResources(cv_name));

		// 1 make query get keywords and save them to a list
		Job job_descp = jobRepo.getJobById(jobID); // pershkrimi i punes

		// tokenizer
		InputStream is = new FileInputStream("C:\\Users\\pc\\eclipse-workspace\\ATS\\ner-skill-model.bin");
		System.out.println("MODEL AVAILABLE or not " + is.available());
		// load the model from file
		TokenNameFinderModel model = new TokenNameFinderModel(is);
		is.close();

		// feed the model to name finder class
		NameFinderME nameFinder = new NameFinderME(model);

		String[] job_descp_list = job_descp.getDescription().split(" ");
		Span nameSpans[] = nameFinder.find(job_descp_list); // skills qe marrim nga job
		System.out.println(nameSpans[0] + "//skillset qe marrim nga job descript splittted");
		System.out.print(job_descp_list);

		CVInformation cv_info = new Gson().fromJson(response.body().charStream(), CVInformation.class);
		System.out.println(cv_info);
		java.util.List<ExperienceModel> cv_skills_list = cv_info.getEksperienca();

		String all_exp = "";

		for (int i = 0; i < cv_skills_list.size(); i++) {
			all_exp += cv_skills_list.get(i).getPershkrimi();
		}

		String[] cv_info_split = all_exp.split(" ");
		Span cvNames[] = nameFinder.find(cv_info_split); // skillset qe marrim nga cv

		System.out.println(cvNames[0] + "//skillset qe marrim nga cv splittted");

		double totalSize = cvNames.length + nameSpans.length;
		ArrayList<String> sameSkills = new ArrayList<>();

		String cv_skill = "";
		for (Span cv_skill_name : cvNames) {
			for (int i = cv_skill_name.getStart(); i < cv_skill_name.getEnd(); i++) {
				cv_skill = cv_skill + cv_info_split[i] + " ";
			}
			System.out.println(cv_skill + "\n" + cv_skill_name.getProb());

		}

		String job_skill = "";

		/*
		 * for(Span job_skill_name: nameSpans){ for (int i=job_skill_name.getStart() ;
		 * i<job_skill_name.getEnd();i++) { job_skill=job_skill + job_descp_list[i] +
		 * " " ; } System.out.println(job_skill + "\t" + job_skill_name.getProb() );
		 * 
		 * }
		 */
		// nameSpans contain all the possible entities detected
		for (Span s : nameSpans) {
			for (int i = s.getStart(); i < s.getEnd(); i++) {
				job_skill = job_skill + job_descp_list[i] + " ";
			}
			if ((cv_skill).contains(job_skill)) {
				sameSkills.add(job_skill); // ketu do te ruhen te perbashketat
			}
		}
		System.out.println(cv_skill  + "CV SKILL");
		System.out.println(job_skill  + "JOB SKILL");

		System.out.println(sameSkills.size() + "TE PERBASHKETAT");

		System.out.println(Arrays.asList(cv_skill).contains(job_skill) + "Contains or not ");
		double sameSkill = sameSkills.size();
		double difference_total = totalSize - sameSkill;
		System.out.println("Totali aftesive " + totalSize + "Diferenca" + difference_total + "TE Perbashketat "
				+ sameSkills.size());
		double difference_score = ((difference_total / totalSize) * 100); // sa nuk jane ne %
		System.out.println(((19 / 24) * 100) + "DIFFERENCE SCORE NE % ");
		System.out.println((difference_total / totalSize) * 100 + "DIFFERENCE SCORE NE % ");

		System.out.println(difference_score + "DIF SCORE");
		double score = 100 - difference_score; // sa % bejne match

		System.out.println("SCORE: " + score);

		CV cv_from_db = cvDao.getCvByName(cv_name);
		String insert_sameSkill = "";
		for (int i = 0; i < sameSkills.size(); i++) {
			insert_sameSkill = insert_sameSkill + sameSkills.get(i);

		}
		rankRepo.addRanking(job_descp, cv_from_db, score, insert_sameSkill);

	}

	public JobDao getJobRepo() {
		return jobRepo;
	}

	public void setJobRepo(JobDao jobRepo) {
		this.jobRepo = jobRepo;
	}

	public RankingDao getRankRepo() {
		return rankRepo;
	}

	public void setRankRepo(RankingDao rankRepo) {
		this.rankRepo = rankRepo;
	}

	public CVDao getCvDao() {
		return cvDao;
	}

	public void setCvDao(CVDao cvDao) {
		this.cvDao = cvDao;
	}

	public File getCVFromResources(String cv_name) {
		try {
			Path folder = Paths.get("C:/Users/pc/eclipse-workspace/ATS/resumes/" + cv_name); // this is base path
			File cv_file = new File(String.valueOf(folder.toAbsolutePath()));
			return cv_file;
		} catch (Exception e) {
			return null;
		}
	}

	public void print() {
		System.out.println("LOL");
	}

}
