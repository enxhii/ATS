package ats.dao;


	import java.io.FileInputStream; 
	import java.io.InputStream;  

	import opennlp.tools.namefind.NameFinderME; 
	import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;  
public class Prova {
	  public static void main(String args[]) throws Exception{
		  
	      InputStream inputStreamTokenizer = new 
	         FileInputStream("C:\\Users\\pc\\eclipse-workspace\\ATS\\skills-trained-model.bin"); 
	      TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer); 
	      //String paragraph = "Mike and Smith are classmates"; 
	      String paragraph = "SQL Python R Linux"; 
	        
	      //Instantiating the TokenizerME class 
	      TokenizerME tokenizer = new TokenizerME(tokenModel); 
	      String tokens[] = tokenizer.tokenize(paragraph); 
	       
	      //Loading the NER-location moodel 
	      InputStream inputStreamNameFinder = new 
	         FileInputStream("C:\\Users\\pc\\eclipse-workspace\\ATS\\skills-trained-model.bin");       
	      TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder); 
	        
	      //Instantiating the NameFinderME class 
	      NameFinderME nameFinder = new NameFinderME(model);      
	        
	      //Finding the names of a location 
	      Span nameSpans[] = nameFinder.find(tokens);        
	      //Printing the spans of the locations in the sentence 
	      for(Span s: nameSpans)        
	         System.out.println(s.toString()+"  "+tokens[s.getStart()]); 
	   }  
	  
	}
