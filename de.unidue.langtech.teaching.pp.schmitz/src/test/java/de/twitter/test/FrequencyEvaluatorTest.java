package de.twitter.test;

import static org.junit.Assert.*;

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.twitter.pipeline.BaselineTweets;
import de.twitter.pipeline.FrequencyEvaluator;
import de.twitter.reader.TweetReader;
import de.twitter.type.DetectedOpinion;
import de.twitter.type.GoldOpinion;

public class FrequencyEvaluatorTest {

	//This test randomly assigns either -1 or +1 to the opinion score
	//The Frequency evaluator is supposed to just print all the gold scores
	//It helps to try to optimize the scoring weights
	
	@Test
	public void testFrequencyEvaluator() throws Exception {
		CollectionReaderDescription reader = CollectionReaderFactory.createReaderDescription(TweetReader.class,
				TweetReader.PARAM_INPUT_TWEETS, "twitter/resources/TweetsShort.txt");
		
		FrequencyEvaluator eval = new FrequencyEvaluator();
		eval.initialize(null);
		int currentLine = 0;
		for (JCas currentJCas : new JCasIterable(reader)) {
			GoldOpinion go = JCasUtil.selectSingle(currentJCas, GoldOpinion.class);
			if (Math.random()<0.5d){
				go.setOpinionScore(-1f);
			}
			else go.setOpinionScore(1f);
			eval.process(currentJCas);
		}
		eval.collectionProcessComplete();
	}

}
