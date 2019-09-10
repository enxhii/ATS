package ats.service;

public interface CvParserService {
	public void extractTextFromPdf(int jobID,String cv_name) throws Exception;
	public void print();

}
