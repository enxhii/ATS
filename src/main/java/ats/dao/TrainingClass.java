package ats.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import opennlp.tools.namefind.BioCodec;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinder;
import opennlp.tools.namefind.TokenNameFinderFactory;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;
import opennlp.tools.util.TrainingParameters;

public class TrainingClass {
 
    public static void main(String[] args) {
 
        // reading training data
        InputStreamFactory in = null;
        try {
            in = new MarkableFileInputStreamFactory(new File("‪‪C:/Users/pc/Desktop/skill.txt"));
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        
        ObjectStream sampleStream = null;
        try {
            sampleStream = new NameSampleDataStream(
                new PlainTextByLineStream(in, StandardCharsets.UTF_8));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
 
        // setting the parameters for training
        TrainingParameters params = new TrainingParameters();
        params.put(TrainingParameters.ITERATIONS_PARAM, 70);
        params.put(TrainingParameters.CUTOFF_PARAM, 1);
 
        // training the model using TokenNameFinderModel class
        TokenNameFinderModel nameFinderModel = null;
        try {
            nameFinderModel = NameFinderME.train("en", null, sampleStream,
                params, TokenNameFinderFactory.create(null, null, Collections.emptyMap(), new BioCodec()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // saving the model to "ner-custom-model.bin" file
        try {
            File output = new File("C:/Users/pc/Desktop/ner-skill-model.bin");
            FileOutputStream outputStream = new FileOutputStream(output);
            nameFinderModel.serialize(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // testing the model and printing the types it found in the input sentence
        TokenNameFinder nameFinder = new NameFinderME(nameFinderModel);
        
        String par="Quality Assurance Technician Job Duties:\r\n" + 
        		"Accomplishes quality assurance human resource objectives by recruiting, selecting, orienting, training, assigning, scheduling, coaching, counseling, and disciplining employees; communicating job expectations; planning, monitoring, appraising, and reviewing job contributions; planning and reviewing compensation actions; enforcing policies and procedures.\r\n" + 
        		"Achieves quality assurance operational objectives by contributing information and analysis to strategic plans and reviews; preparing and completing action plans; implementing production, productivity, quality, and customer-service standards; identifying and resolving problems; completing audits; determining system improvements; implementing change.\r\n" + 
        		"Meets quality assurance financial objectives by estimating requirements; preparing an annual budget; scheduling expenditures; analyzing variances; initiating corrective actions.\r\n" + 
        		"Develops quality assurance plans by conducting hazard analyses; identifying critical control points and preventive measures; establishing critical limits, monitoring procedures, corrective actions, and verification procedures; monitoring inventories.\r\n" + 
        		"Validates quality processes by establishing product specifications and quality attributes; measuring production; documenting evidence; determining operational and performance qualification; writing and updating quality assurance procedures.\r\n" + 
        		"Maintains and improves product quality by completing product, company, system, compliance, and surveillance audits; investigating customer complaints; collaborating with other members of management to develop new product and engineering designs, and manufacturing and training methods.\r\n" + 
        		"Prepares quality documentation and reports by collecting, analyzing and summarizing information and trends including failed processes, stability studies, recalls, corrective actions, and re-validations.\r\n" + 
        		"Updates job knowledge by studying trends in and developments in quality management; participating in educational opportunities; reading professional publications; maintaining personal networks; participating in professional organizations.\r\n" + 
        		"Enhances department and organization reputation by accepting ownership for accomplishing new and different requests; exploring opportunities to add value to job accomplishments.\r\n" + 
        		"Quality Assurance Technician Skills and Qualifications:\r\n" + 
        		"Process Improvement, Analyzing Information , Strategic Planning, Verbal Communication, Informing Others, Quality Engineering, Emphasizing Excellence, Pharmacology, Attention to Detail, Thoroughness, Dealing with Complexity\r\n" + 
        		"\r\n" + 
        		"";
        String[] testSentence =par.split(" ");
        
 
        System.out.println("Finding types in the test sentence..");
        Span[] names = nameFinder.find(testSentence);
        for(Span name:names){
            String personName="";
            for(int i=name.getStart();i<name.getEnd();i++){
                personName+=testSentence[i]+" ";
            }
            System.out.println(name.getType()+" : "+personName+"\t [probability="+name.getProb()+"]");
        }
    }
 
}