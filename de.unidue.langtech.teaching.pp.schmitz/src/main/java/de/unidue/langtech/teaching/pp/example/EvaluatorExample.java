package de.unidue.langtech.teaching.pp.example;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.twitter.type.DetectedOpinion;
import de.twitter.type.GoldOpinion;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;
import de.unidue.langtech.teaching.pp.type.GoldLanguage;

public class EvaluatorExample
    extends JCasAnnotator_ImplBase
{

    private int correct;
    private int nrOfDocuments;
    
    private int nrOfPositiveDocuments;
    private int nrOfPositiveDocumentsDetectedAsNegative;
    private int nrOfPositiveDocumentsDetectedAsNeutral;
    private int nrOfPositiveDocumentsDetectedAsPositive;
    
    private int nrOfNegativeDocuments;
    private int nrOfNegativeDocumentsDetectedAsNegative;
    private int nrOfNegativeDocumentsDetectedAsNeutral;
    private int nrOfNegativeDocumentsDetectedAsPositive;
    
    private int nrOfNeutralDocuments;
    private int nrOfNeutralDocumentsDetectedAsNegative;
    private int nrOfNeutralDocumentsDetectedAsNeutral;
    private int nrOfNeutralDocumentsDetectedAsPositive;
    
    
    
    
    /* 
     * This is called BEFORE any documents are processed.
     */
    @Override
    public void initialize(UimaContext context)
        throws ResourceInitializationException
    {
        super.initialize(context);
        correct = 0;
        nrOfDocuments = 0;
        
        nrOfPositiveDocuments = 0;
        nrOfPositiveDocumentsDetectedAsNegative = 0;
        nrOfPositiveDocumentsDetectedAsNeutral = 0;
        nrOfPositiveDocumentsDetectedAsPositive = 0;
        
        nrOfNegativeDocuments = 0;
        nrOfNegativeDocumentsDetectedAsNegative = 0;
        nrOfNegativeDocumentsDetectedAsNeutral = 0;
        nrOfNegativeDocumentsDetectedAsPositive = 0;
        
        nrOfNeutralDocuments = 0;
        nrOfNeutralDocumentsDetectedAsNegative = 0;
        nrOfNeutralDocumentsDetectedAsNeutral = 0;
        nrOfNeutralDocumentsDetectedAsPositive = 0;
    }
    
    
    /* 
     * This is called ONCE for each document
     */
    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        
    	DetectedOpinion detected = JCasUtil.selectSingle(jcas, DetectedOpinion.class);
    	GoldOpinion actual = JCasUtil.selectSingle(jcas, GoldOpinion.class);

        System.out.println(actual.getOpinion() + " detected as " + detected.getOpinion());
        
        if (actual.getOpinion().equals(detected.getOpinion())) correct++;
        
        /*
         * Statistics department.... Funny stuff
         * 
         * Increases all the counters that should be increased
         */
        
        nrOfDocuments++;
        
        if (actual.getOpinion().equals("positive")){
        	nrOfPositiveDocuments++;
        	switch (detected.getOpinion()){
        	case "positive" : nrOfPositiveDocumentsDetectedAsPositive++; break;
        	case "negative" : nrOfPositiveDocumentsDetectedAsNegative++; break;
        	case "neutral" : nrOfPositiveDocumentsDetectedAsNeutral++; break;
        	}
        }
        if (actual.getOpinion().equals("negative")){
        	nrOfNegativeDocuments++;
        	switch (detected.getOpinion()){
        	case "positive" : nrOfNegativeDocumentsDetectedAsPositive++; break;
        	case "negative" : nrOfNegativeDocumentsDetectedAsNegative++; break;
        	case "neutral" : nrOfNegativeDocumentsDetectedAsNeutral++; break;
        	}
        }
        if (actual.getOpinion().equals("neutral")){
        	nrOfNeutralDocuments++;
        	switch (detected.getOpinion()){
        	case "positive" : nrOfNeutralDocumentsDetectedAsPositive++; break;
        	case "negative" : nrOfNeutralDocumentsDetectedAsNegative++; break;
        	case "neutral" : nrOfNeutralDocumentsDetectedAsNeutral++; break;
        	}
        }
    }


    /* 
     * This is called AFTER all documents have been processed.
     */
    @Override
    public void collectionProcessComplete()
        throws AnalysisEngineProcessException
    {
        super.collectionProcessComplete();
        
        System.out.println("---------BEGIN OF EVALUATION---------");
        
        System.out.println(correct + " out of " + nrOfDocuments + " are correct.");
    	System.out.println("--------------------------------");
    	
        System.out.println("There were "+nrOfPositiveDocuments+" positive documents.");
        System.out.println("Of these "+nrOfPositiveDocumentsDetectedAsPositive+" were detected CORRECT AS POSITIVE");
        System.out.println("Of these "+nrOfPositiveDocumentsDetectedAsNegative+" were detected FALSE AS NEGATIVE");
        System.out.println("Of these "+nrOfPositiveDocumentsDetectedAsNeutral+" were detected FALSE AS NEUTRAL");
    	System.out.println("--------------------------------");
        
        System.out.println("There were "+nrOfNegativeDocuments+" negative documents.");
        System.out.println("Of these "+nrOfNegativeDocumentsDetectedAsPositive+" were detected FALSE AS POSITIVE");
        System.out.println("Of these "+nrOfNegativeDocumentsDetectedAsNegative+" were detected CORRECT AS NEGATIVE");
        System.out.println("Of these "+nrOfNegativeDocumentsDetectedAsNeutral+" were detected FALSE AS NEUTRAL");
    	System.out.println("--------------------------------");
        
        System.out.println("There were "+nrOfNeutralDocuments+" neutral documents.");
        System.out.println("Of these "+nrOfNeutralDocumentsDetectedAsPositive+" were detected FALSE AS POSITIVE");
        System.out.println("Of these "+nrOfNeutralDocumentsDetectedAsNegative+" were detected FALSE AS NEGATIVE");
        System.out.println("Of these "+nrOfNeutralDocumentsDetectedAsNeutral+" were detected CORRECT AS NEUTRAL");
    	System.out.println("--------------------------------");
    	
    	int detectedPositives = nrOfPositiveDocumentsDetectedAsPositive+nrOfNegativeDocumentsDetectedAsPositive+nrOfNeutralDocumentsDetectedAsPositive;
    	System.out.println("The system detected a total of " +detectedPositives+ " positive entrys (there were " + nrOfPositiveDocuments + ")");
        
    	int detectedNegatives = nrOfPositiveDocumentsDetectedAsNegative+nrOfNegativeDocumentsDetectedAsNegative+nrOfNeutralDocumentsDetectedAsNegative;
    	System.out.println("The system detected a total of " +detectedNegatives+ " negative entrys (there were " + nrOfNegativeDocuments + ")");
        
    	int detectedNeutrals = nrOfPositiveDocumentsDetectedAsNeutral+nrOfNegativeDocumentsDetectedAsNeutral+nrOfNeutralDocumentsDetectedAsNeutral;
    	System.out.println("The system detected a total of " +detectedNeutrals+ " neutral entrys (there were " + nrOfNeutralDocuments + ")");
        
    	
    }
}