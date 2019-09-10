package ats.serviceImpl;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

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
public class CvParserImplService  implements CvParserService{
	
	@Autowired
	private JobDao jobRepo; 
	
	@Autowired 
	private RankingDao rankRepo; 
	
	
	@Autowired
	private CVDao cvDao;
	final static Logger LOGGER = LogManager.getLogger(CvParserImplService.class);

	@SuppressWarnings("unlikely-arg-type")
	public void extractTextFromPdf(int jobID,String cv_name) throws Exception {

		LOGGER.debug("JOB ID  ..." + jobID);
		LOGGER.debug("CV NAME ..." + cv_name);
//        HttpHeaders headers = new HttpHeaders();     //set the headers first so we know we will send a pdf as a file
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);  //this is for the multipart form data 
//
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("cv", getCVFromResources(cv_name));
//        
//        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);  //create a multimap
        String serverUrl = "http://localhost:3000/"; //this is where we are sending the request
//        RestTemplate restTemplate = new RestTemplate(); //create new rest template, this is integrated in spring 
//        ResponseEntity<CVInformation> response = restTemplate.postForEntity(serverUrl, requestEntity, CVInformation.class);  //get the data from the server

		System.out.println("Before call ...");
		OkHttpClient client = new OkHttpClient();
	    RequestBody formBody = new MultipartBody.Builder()
	        .setType(MultipartBody.FORM)
	        .addFormDataPart("cv","cv",
	            RequestBody.create(MediaType.parse("application/pdf"), getCVFromResources(cv_name)))
	        .build();  
	    Request request = new Request.Builder().url(serverUrl).post(formBody).build();
	    Response response = null;
	    try {
	    	response= client.newCall(request).execute();	
			System.out.println(response= client.newCall(request).execute());// TODO: handle exception

	    }catch (Exception e) {
			System.out.println("ERR REQUEST");// TODO: handle exception
		}
	  
		
	    System.out.println("CV PATH : " + getCVFromResources(cv_name));
		
        //1 make query get keywords and save them to a list
        Job job_descp = jobRepo.getJobById(jobID); //pershkrimi i punes 
        
        //  tokenizer
        InputStream is = new FileInputStream("C:\\Users\\pc\\eclipse-workspace\\ATS\\skills-trained-model.bin");
        
        // load the model from file
        TokenNameFinderModel model = new TokenNameFinderModel(is);
        is.close();
 
        // feed the model to name finder class
        NameFinderME nameFinder = new NameFinderME(model);
 
        String[] job_descp_list = job_descp.getDescription().split(" ");
        Span nameSpans[] = nameFinder.find(job_descp_list);  //skills qe marrim nga job 
        System.out.println(nameSpans.length + "//skillset qe marrim nga job descript splittted");

        CVInformation cv_info = new Gson().fromJson(response.body().charStream(), CVInformation.class);
        System.out.println(cv_info);
        java.util.List<ExperienceModel> cv_skills_list = cv_info.getEksperienca();
        
        String all_exp = "";
        
        for(int i =0 ;i < cv_skills_list.size() ;i++) {
        	all_exp += cv_skills_list.get(i).getPershkrimi();
        }
        
        String[] cv_info_split = all_exp.split(" ");
        Span cvNames[] = nameFinder.find(cv_info_split); //skillset qe marrim nga cv
        System.out.println(cvNames.length + "//skillset qe marrim nga cv splittted");
        
        int totalSize = cvNames.length + nameSpans.length;
        ArrayList<String> sameSkills = new ArrayList<>();
        System.out.println(nameSpans.length +" Skillset nga job  ");
        System.out.println(cvNames.length +" Skillset qe marrim nga cv ");
        

        // nameSpans contain all the possible entities detected
        for(Span s: nameSpans){
            if(Arrays.asList(cvNames).contains(s.toString())) {
            	sameSkills.add(s.toString()); //ketu do te ruhen te perbashketat
            	System.out.println(s.toString() +"TE PERBASHKETAT");
            }
        }
        
        int difference_total  = totalSize - sameSkills.size(); 
        double difference_score = ((difference_total)/(totalSize))*100; //sa nuk jane ne %
        System.out.println(difference_score);
        double score = 100 - difference_score; //sa % bejne match
        
        System.out.println("SCORE: " + score);
        
        CV cv_from_db = cvDao.getCvByName(cv_name);
        
        rankRepo.addRanking(job_descp, cv_from_db, score); 

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

	public File getCVFromResources(String cv_name){
		try {
    	Path folder = Paths.get("C:/Users/pc/eclipse-workspace/ATS/resumes/" + cv_name); //this is base path 
    	File cv_file = new File(String.valueOf(folder.toAbsolutePath()));
        return cv_file;
		}catch (Exception e) {
			return null;
		}
    }
	
	
	public void print() {
		System.out.println("LOL");
	}

}
