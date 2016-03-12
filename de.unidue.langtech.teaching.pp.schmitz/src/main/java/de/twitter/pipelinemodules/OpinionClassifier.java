package de.twitter.pipelinemodules;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.component.initialize.ConfigurationParameterInitializer;
import org.apache.uima.fit.component.initialize.ExternalResourceInitializer;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.twitter.reader.SubjectClueReader;
import de.twitter.type.DetectedOpinion;
import de.twitter.type.GoldOpinion;
import de.twitter.utils.SubjClue;

/*
 * The OpinionClassifier will take several factors into consideration when tagging a tweet.
 * 1st it looks up every single word of the jCas in a given opinionDictionary, that gives clues on the opinion that a certain word represents.
 */

public class OpinionClassifier extends JCasAnnotator_ImplBase {

	/*
	 * The subjectDictionary gets initialized here for further use in the
	 * pipeline
	 */
	private List<SubjClue> subjectDictionary;

	@Override
	public void initialize(final UimaContext context) throws ResourceInitializationException {
		super.initialize(context);

		subjectDictionary = SubjectClueReader
				.generateSubjClues("twitter/resources/subjectivity_clues/subjectClues.tff");

	}

	/*
	 * When processed all the words from the input tweet get checked if they are
	 * contained in the dictionary. based on the findings an approximation score
	 * is computed that will be turned into a distinct classification after
	 */
	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {

		int strongNeutrals = 0;
		int weakNeutrals = 0;
		int strongNegatives = 0;
		int weakNegatives = 0;
		int strongPositives = 0;
		int weakPositives = 0;
		int positiveSmileys = 0;
		int negativeSmileys = 0;

		/*
		 * store current tokens in tokens
		 */
		Collection<Token> tokens = JCasUtil.select(jcas, Token.class);

		/*
		 * find occurence of tokens in the dictionary and increase corrosponding
		 * counter.
		 */
		for (Token t : tokens) {
			for (int i = 0; i < subjectDictionary.size(); i++) {
				SubjClue currentWord = subjectDictionary.get(i);
				if (currentWord.equals(t.getCoveredText())) {

					if (currentWord.isSmiley()) {

						if (currentWord.getPriorpolarity().equals("positive")) {
							positiveSmileys++;
						} else {
							negativeSmileys++;
						}

					} else {
						switch (currentWord.getPriorpolarity()) {
						case "negative":
							if (currentWord.isStrong()) {
								strongNegatives++;
							} else
								weakNegatives++;
							break;
						case "positive":
							if (currentWord.isStrong()) {
								strongPositives++;
							} else
								weakPositives++;
							break;
						case "neutral":
							if (currentWord.isStrong()) {
								strongNeutrals++;
							} else
								weakNeutrals++;
							break;
						}
					}

					break;
				}
			}
		}

		float approximation = strongNegatives * -4.6f + weakNegatives * -2f + weakPositives * 1.5f
				+ strongPositives * 3f + 7f * positiveSmileys + -7f * negativeSmileys;
//		float neutralImpact = weakNeutrals * 1f + strongNeutrals * 1.3f;
//		if (neutralImpact != 0) {
//			approximation /= neutralImpact;
//		}

		GoldOpinion goldApprox = JCasUtil.selectSingle(jcas, GoldOpinion.class);
		goldApprox.setOpinionScore(approximation);

		if (approximation < -0f) {
			DetectedOpinion opinionAnno = new DetectedOpinion(jcas);
			opinionAnno.setOpinion("negative");
			opinionAnno.setOpinionScore(approximation);
			opinionAnno.addToIndexes();
		} else if (approximation > 0.5f) {
			DetectedOpinion opinionAnno = new DetectedOpinion(jcas);
			opinionAnno.setOpinion("positive");
			opinionAnno.setOpinionScore(approximation);
			opinionAnno.addToIndexes();
		} else {
			DetectedOpinion opinionAnno = new DetectedOpinion(jcas);
			opinionAnno.setOpinion("neutral");
			opinionAnno.setOpinionScore(approximation);
			opinionAnno.addToIndexes();
		}

	}

}
