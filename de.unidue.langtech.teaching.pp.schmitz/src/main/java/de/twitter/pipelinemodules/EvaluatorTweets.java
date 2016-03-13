package de.twitter.pipelinemodules;


import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.twitter.type.DetectedOpinion;
import de.twitter.type.GoldOpinion;

public class EvaluatorTweets
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
    
    private float actualPositiveSum;
    private float actualNegativeSum;
    private float actualNeutralSum;
    
    private float detectedPositiveSum;
    private float detectedNegativeSum;
    private float detectedNeutralSum;
    
    
    
    
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
        
        actualNegativeSum = 0f;
        actualNeutralSum = 0f;
        actualPositiveSum = 0f;
        
        detectedNegativeSum = 0f;
        detectedNeutralSum = 0f;
        detectedPositiveSum = 0f;
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

        //System.out.println(actual.getOpinion() + " detected as " + detected.getOpinion());
        
        if (actual.getOpinion().equals(detected.getOpinion())) correct++;
        
        /*
         * Statistics department.... Funny stuff
         * 
         * Increases all the counters that should be increased
         */
        
        nrOfDocuments++;
        
        if (actual.getOpinion().equals("positive")){
        	actualPositiveSum += actual.getOpinionScore();
        	nrOfPositiveDocuments++;
        	switch (detected.getOpinion()){
        	case "positive" : nrOfPositiveDocumentsDetectedAsPositive++; detectedPositiveSum+=detected.getOpinionScore(); break;
        	case "negative" : nrOfPositiveDocumentsDetectedAsNegative++; detectedNegativeSum+=detected.getOpinionScore();break;
        	case "neutral" : nrOfPositiveDocumentsDetectedAsNeutral++; detectedNeutralSum+=detected.getOpinionScore();break;
        	}
        }
        if (actual.getOpinion().equals("negative")){
        	actualNegativeSum += actual.getOpinionScore();
        	nrOfNegativeDocuments++;
        	switch (detected.getOpinion()){
        	case "positive" : nrOfNegativeDocumentsDetectedAsPositive++; detectedPositiveSum+=detected.getOpinionScore();break;
        	case "negative" : nrOfNegativeDocumentsDetectedAsNegative++; detectedNegativeSum+=detected.getOpinionScore();break;
        	case "neutral" : nrOfNegativeDocumentsDetectedAsNeutral++; detectedNeutralSum+=detected.getOpinionScore();break;
        	}
        }
        if (actual.getOpinion().equals("neutral")){
        	actualNeutralSum += actual.getOpinionScore();
        	nrOfNeutralDocuments++;
        	switch (detected.getOpinion()){
        	case "positive" : nrOfNeutralDocumentsDetectedAsPositive++; detectedPositiveSum+=detected.getOpinionScore();break;
        	case "negative" : nrOfNeutralDocumentsDetectedAsNegative++; detectedNegativeSum+=detected.getOpinionScore();break;
        	case "neutral" : nrOfNeutralDocumentsDetectedAsNeutral++; detectedNeutralSum+=detected.getOpinionScore();break;
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
        float percent = (float)correct/(float)nrOfDocuments*100;
        System.out.println("That is a rate of "+percent+"%");
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
    	float positivePrecision = ((float)nrOfPositiveDocumentsDetectedAsPositive/(float)detectedPositives*100);
    	float positiveRecall = ((float)nrOfPositiveDocumentsDetectedAsPositive/(float)nrOfPositiveDocuments*100);
    	System.out.println("The system detected a total of " +detectedPositives+ " positive entrys (there were " + nrOfPositiveDocuments + ")");
    	System.out.println("\t The precision score for positive documents is: " + positivePrecision+"%");       
        System.out.println("\t The recall score for positive documents is: " + positiveRecall+"%");
        
    	
    	int detectedNegatives = nrOfPositiveDocumentsDetectedAsNegative+nrOfNegativeDocumentsDetectedAsNegative+nrOfNeutralDocumentsDetectedAsNegative;
    	float negativePrecision = ((float)nrOfNegativeDocumentsDetectedAsNegative/(float)detectedNegatives*100);
    	float negativeRecall = ((float)nrOfNegativeDocumentsDetectedAsNegative/(float)nrOfNegativeDocuments*100);
    	System.out.println("The system detected a total of " +detectedNegatives+ " negative entrys (there were " + nrOfNegativeDocuments + ")");
        System.out.println("\t The precision score for negative documents is: " + negativePrecision+"%");       
        System.out.println("\t The recall score for negative documents is: " + negativeRecall+"%");
        
        
    	int detectedNeutrals = nrOfPositiveDocumentsDetectedAsNeutral+nrOfNegativeDocumentsDetectedAsNeutral+nrOfNeutralDocumentsDetectedAsNeutral;
    	float neutralPrecision = ((float)nrOfNeutralDocumentsDetectedAsNeutral/(float)detectedNeutrals*100);
    	float neutralRecall = ((float)nrOfNeutralDocumentsDetectedAsNeutral/(float)nrOfNeutralDocuments*100);
    	System.out.println("The system detected a total of " +detectedNeutrals+ " neutral entrys (there were " + nrOfNeutralDocuments + ")");
    	System.out.println("\t The precision score for neutral documents is: " + neutralPrecision+"%");       
        System.out.println("\t The recall score for neutral documents is: " + neutralRecall+"%");
         
    	
    	System.out.println("-----------------------");
    	System.out.println("-----------------------");
    	
    	float actualPositiveAverageScore = actualPositiveSum / nrOfPositiveDocuments;
    	System.out.println("The average score for actually positive documents was: "+actualPositiveAverageScore);
    	float detectedPositiveAverageScore = detectedPositiveSum / detectedPositives;
    	System.out.println("The average score for detected positive documents was: "+detectedPositiveAverageScore);
    	
    	System.out.println("-----------------------");
    	
    	float actualNegativeAverageScore = actualNegativeSum / nrOfNegativeDocuments;
    	System.out.println("The average score for actually negative documents was: "+actualNegativeAverageScore);
    	float detectedNegativeAverageScore = detectedNegativeSum / detectedNegatives;
    	System.out.println("The average score for detected negative documents was: "+detectedNegativeAverageScore);
    	
    	System.out.println("-----------------------");
    	
    	float actualNeutralAverageScore = actualNeutralSum / nrOfNeutralDocuments;
    	System.out.println("The average score for actually neutral documents was: "+actualNeutralAverageScore);
    	float detectedNeutralAverageScore = detectedNeutralSum / detectedNeutrals;
    	System.out.println("The average score for detected neutral documents was: "+detectedNeutralAverageScore);
    	
    	System.out.println("-----------------------");
    	float f1Positive = (2*positivePrecision*positiveRecall)/(positivePrecision+positiveRecall);
    	float f1Negative = (2*negativePrecision*negativeRecall)/(negativePrecision+negativeRecall);
    	float f1Neutral = (2*neutralPrecision*neutralRecall)/(neutralPrecision+neutralRecall);
    	System.out.println("F1-Measure for positive: "+f1Positive);
    	System.out.println("F1-Measure for negative: "+f1Negative);
    	System.out.println("F1-Measure for neutral: "+f1Neutral);
    	System.out.println("-----------------------");
    	System.out.println("-----------------------");
    	System.out.println("Final Score following SemEval-Scoring: "+(f1Positive+f1Negative)/2);
    }
}