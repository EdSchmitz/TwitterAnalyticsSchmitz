package de.twitter.pipelinemodules;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.twitter.type.GoldOpinion;

public class FrequencyEvaluator extends JCasAnnotator_ImplBase{

	private List<Float> positiveValues;
	private List<Float> neutralValues;
	private List<Float> negativeValues;
	
	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException{
		super.initialize(context);
		
		positiveValues = new ArrayList<Float>();
		neutralValues  = new ArrayList<Float>();
		negativeValues = new ArrayList<Float>();
		
	}
	
	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {

		GoldOpinion gold = JCasUtil.selectSingle(jcas, GoldOpinion.class);
		switch(gold.getOpinion()){
		case "positive" : positiveValues.add(gold.getOpinionScore()); break;
		case "negative" : negativeValues.add(gold.getOpinionScore()); break;
		case "neutral" : neutralValues.add(gold.getOpinionScore()); break;
		}
		
		
		
	}

	@Override
	public void collectionProcessComplete() throws AnalysisEngineProcessException {
		super.collectionProcessComplete();
		
		System.out.println("--------------------------------");
		System.out.println("------Begin of Frequencys-------");
		
		
		String parsablePositives = "";
		for (float f : positiveValues){
			parsablePositives += f+"\t";
		}
		parsablePositives = parsablePositives.replace(".", ",");
		System.out.println("Here are all the Scores of positives Documents:");
		System.out.println(parsablePositives);
		
		String parsableNegatives = "";
		for (float f : negativeValues){
			parsableNegatives += f+"\t";
		}
		parsableNegatives = parsableNegatives.replace(".", ",");
		System.out.println("Here are all the Scores of negative Documents:");
		System.out.println(parsableNegatives);
		
		String parsableNeutrals = "";
		for (float f : neutralValues){
			parsableNeutrals += f+"\t";
		}
		parsableNeutrals = parsableNeutrals.replace(".", ",");
		System.out.println("Here are all the Scores of neutral Documents:");
		System.out.println(parsableNeutrals);
	}
	
}
