package de.twitter.pipeline;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.twitter.type.DetectedOpinion;
import de.twitter.type.GoldOpinion;

public class wrongClassifiedDisplayer extends JCasAnnotator_ImplBase{

	// insert "negative" "neutral" or "positive" as needed, to check the wrong classified tweets
	// so you can manually try to find similarities
	private static final String SHOULDBE= "positive";
	private static final String ISDETECTED= "negative";
	
	private List<String> wrongOnes;
	
	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException{
		super.initialize(context);
		
		wrongOnes = new ArrayList<String>();
		
	}
	
	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		
		GoldOpinion gold = JCasUtil.selectSingle(jcas, GoldOpinion.class);
		DetectedOpinion detected = JCasUtil.selectSingle(jcas, DetectedOpinion.class);
		
		if (gold.getOpinion().equals(SHOULDBE)&&detected.getOpinion().equals(ISDETECTED)){
			wrongOnes.add("The score is: "+detected.getOpinionScore() +" The tweet is:"+ jcas.getDocumentText());
		}
		
		
		
	}
	
	@Override
	public void collectionProcessComplete() throws AnalysisEngineProcessException {
		super.collectionProcessComplete();
		
		for (String wrong : wrongOnes){
			System.out.println(wrong);
		}
	}

}
