package de.twitter.executablePipeline;

import org.apache.uima.fit.component.CasDumpWriter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.arktools.ArktweetTokenizer;
import de.twitter.pipelinemodules.EvaluatorTweets;
import de.twitter.pipelinemodules.FrequencyEvaluator;
import de.twitter.pipelinemodules.OpinionClassifier;
import de.twitter.reader.TweetReader;
public class FullTweetPipeline
{

    public static void main(String[] args)
        throws Exception
    {
    	/*
    	 * This Pipeline requires some exterior input.
    	 */
    	
    	/*
    	 * Input options:
    	 * "tweets1k.txt" for 1001 tweets
    	 * "twitter/resources/twitter_training_complete.txt" for 11338 tweets
    	 */
    	
    	/*
    	 * This pipeline will read and analyze the full 11338 tweets.
    	 * Its output will be displayed on the console
    	 */
    	
        SimplePipeline.runPipeline(
                CollectionReaderFactory.createReader(
                        TweetReader.class,
                        TweetReader.PARAM_INPUT_TWEETS, "twitter/resources/twitter_training_complete.txt"
                ),
                AnalysisEngineFactory.createEngineDescription(ArktweetTokenizer.class),
                AnalysisEngineFactory.createEngineDescription(OpinionClassifier.class),
                AnalysisEngineFactory.createEngineDescription(EvaluatorTweets.class)
//                AnalysisEngineFactory.createEngineDescription(CasDumpWriter.class),
//                AnalysisEngineFactory.createEngineDescription(FrequencyEvaluator.class)
                
        );
    }
}