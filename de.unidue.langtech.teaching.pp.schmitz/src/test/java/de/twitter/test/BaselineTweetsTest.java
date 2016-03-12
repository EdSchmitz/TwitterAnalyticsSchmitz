package de.twitter.test;

import static org.junit.Assert.*;

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.twitter.pipeline.BaselineTweets;
import de.twitter.reader.TweetReader;
import de.twitter.type.DetectedOpinion;

public class BaselineTweetsTest {

	@Test
	public void testBaselineTweets() throws Exception {
		CollectionReaderDescription reader = CollectionReaderFactory.createReaderDescription(TweetReader.class,
				TweetReader.PARAM_INPUT_TWEETS, "twitter/resources/TweetsShort.txt");
		
		BaselineTweets bt = new BaselineTweets();

		int currentLine = 0;
		for (JCas currentJCas : new JCasIterable(reader)) {
			
			bt.process(currentJCas);
			
			DetectedOpinion detectedOpinion = JCasUtil.selectSingle(currentJCas, DetectedOpinion.class);
			switch (currentLine) {
			case 0:
				assertEquals("positive", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 1:
				assertEquals("positive", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 2:
				assertEquals("positive", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 3:
				assertEquals("positive", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 4:
				assertEquals("positive", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 5:
				assertEquals("positive", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 6:
				assertEquals("positive", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 7:
				assertEquals("positive", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 8:
				assertEquals("positive", detectedOpinion.getOpinion());
				currentLine++;
				continue;

			}
		}
	}

}
