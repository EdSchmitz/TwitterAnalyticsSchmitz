package de.twitter.test;

import static org.junit.Assert.*;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.arktools.ArktweetTokenizer;
import de.twitter.pipelinemodules.OpinionClassifier;
import de.twitter.reader.TweetReader;
import de.twitter.type.DetectedOpinion;

public class OpinionClassifierTest {

	//FIXME Something is wrong in here, i can't quite figure out what
	// When runnung the test the tokenizer refuses to tokenize the cas
	
	@Test
	public void testOpinionClassifier() throws Exception {
		CollectionReaderDescription reader = CollectionReaderFactory.createReaderDescription(TweetReader.class,
				TweetReader.PARAM_INPUT_TWEETS, "twitter/resources/TweetsShort.txt");
		
		OpinionClassifier oc = new OpinionClassifier();
		ArktweetTokenizer tokenizer = new ArktweetTokenizer();
		
		int currentLine = 0;
		for (JCas currentJCas : new JCasIterable(reader)) {
			System.out.println("Checkpoint 1");
			tokenizer.process(currentJCas);
			System.out.println("Checkpoint 2");
			oc.process(currentJCas);
			
			DetectedOpinion detectedOpinion = JCasUtil.selectSingle(currentJCas, DetectedOpinion.class);
			switch (currentLine) {
			case 0:
				System.out.println(detectedOpinion.getOpinionScore());
				assertEquals("positive", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 1:
				assertEquals("negative", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 2:
				assertEquals("negative", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 3:
				assertEquals("negative", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 4:
				assertEquals("neutral", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 5:
				assertEquals("neutral", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 6:
				assertEquals("positive", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 7:
				assertEquals("negative", detectedOpinion.getOpinion());
				currentLine++;
				continue;
			case 8:
				assertEquals("neutral", detectedOpinion.getOpinion());
				currentLine++;
				continue;

			}
		}
	}

}
