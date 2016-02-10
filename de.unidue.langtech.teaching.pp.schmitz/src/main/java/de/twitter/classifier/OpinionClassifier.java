package de.twitter.classifier;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.twitter.reader.SubjClue;
import de.twitter.reader.SubjectClueReader;
import de.twitter.type.DetectedOpinion;

/*
 * The OpinionClassifier will take several factors into consideration when tagging a tweet.
 * 1st it looks up every single word of the jCas in a given opinionDictionary, that gives clues on the opinion that a certain word represents.
 */

public class OpinionClassifier extends JCasAnnotator_ImplBase {
	
	/*
	 * FIXME
	 * Here is a terrible way to include the subjecDictionary... I think
	 */
	List<SubjClue> subjectDictionary = SubjectClueReader.generateSubjClues("twitter/resources/subjectivity_clues/subjectClues.tff");
	
	
	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		
		int strongNeutrals = 0;
		int weakNeutrals = 0;
		int strongNegatives = 0;
		int weakNegatives = 0;
		int strongPositives = 0;
		int weakPositives = 0;
		
		/*
		 * store current tokens in tokens
		 */
		Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
		
		/*
		 * find occurence of tokens in the dictionary and increase corrosponding counter.
		 */
		for (Token t : tokens){
			Iterator dict = subjectDictionary.iterator();
			for (int i = 0; i < subjectDictionary.size(); i++){
				SubjClue currentWord = subjectDictionary.get(i);
				if (currentWord.equals(t.getCoveredText())){
					switch (currentWord.getPriorpolarity()){
					case "negative" :
						if (currentWord.isStrong()){
							strongNegatives++;
						}
						else weakNegatives++;
						break;
					case "positive" :
						if (currentWord.isStrong()){
							strongPositives++;
						}
						else weakPositives++;
						break;
					case "neutral" :
						if (currentWord.isStrong()){
							strongNeutrals++;
						}
						else weakNeutrals++;
						break;
					}
					break;
				}
			}		
		}
		
		int approximation = strongNegatives*-2+weakNegatives*-1+weakPositives*1+strongPositives*2;
		
		if (approximation < 0){
	        DetectedOpinion opinionAnno = new DetectedOpinion(jcas);
	        opinionAnno.setOpinion("negative");
	        opinionAnno.addToIndexes();
		}
		else if (approximation > 0){
	        DetectedOpinion opinionAnno = new DetectedOpinion(jcas);
	        opinionAnno.setOpinion("positive");
	        opinionAnno.addToIndexes();
		}
		else {
	        DetectedOpinion opinionAnno = new DetectedOpinion(jcas);
	        opinionAnno.setOpinion("neutral");
	        opinionAnno.addToIndexes();
		}


	}

}
