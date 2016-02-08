package de.twitter.test;

import static org.junit.Assert.*;

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.twitter.reader.TweetReader;
import de.twitter.type.GoldOpinion;

public class TweetReaderTest {

	@Test
	public void testTweetReader() throws Exception {
		CollectionReaderDescription reader = CollectionReaderFactory.createReaderDescription(TweetReader.class,
				TweetReader.PARAM_INPUT_TWEETS, "twitter/resources/TweetsShort.txt");

		/*
		 * The "TweetsShort.txt" is a small portion of the complete set. It
		 * contains 9 Tweets, some are followed by an empty line, others are
		 * not. (13 Lines in total) The correct readings are as follows: 
		 * 1. positive 
		 * 2. negative 
		 * 3. negative 
		 * 4. negative 
		 * 5. neutral 
		 * 6. neutral 
		 * 7. positive 
		 * 8. negative 
		 * 9. neutral
		 * 
		 * 
		 */

		int currentLine = 0;
		for (JCas currentJCas : new JCasIterable(reader)) {
			GoldOpinion opinionAnno = JCasUtil.selectSingle(currentJCas, GoldOpinion.class);
			switch (currentLine) {
			case 0:
				assertEquals("positive", opinionAnno.getOpinion());
				currentLine++;
				continue;
			case 1:
				assertEquals("negative", opinionAnno.getOpinion());
				currentLine++;
				continue;
			case 2:
				assertEquals("negative", opinionAnno.getOpinion());
				currentLine++;
				continue;
			case 3:
				assertEquals("negative", opinionAnno.getOpinion());
				currentLine++;
				continue;
			case 4:
				assertEquals("neutral", opinionAnno.getOpinion());
				currentLine++;
				continue;
			case 5:
				assertEquals("neutral", opinionAnno.getOpinion());
				currentLine++;
				continue;
			case 6:
				assertEquals("positive", opinionAnno.getOpinion());
				currentLine++;
				continue;
			case 7:
				assertEquals("negative", opinionAnno.getOpinion());
				currentLine++;
				continue;
			case 8:
				assertEquals("neutral", opinionAnno.getOpinion());
				currentLine++;
				continue;

			}
		}
	}

}
