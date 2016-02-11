package de.unidue.langtech.teaching.pp.example.pipeline;

import java.util.Iterator;
import java.util.List;

import org.apache.uima.fit.component.CasDumpWriter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.arktools.ArktweetTokenizer;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordParser;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordPosTagger;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.util.StanfordAnnotator;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.twitter.classifier.OpinionClassifier;
import de.twitter.reader.TweetReader;
import de.twitter.utils.SubjClue;
import de.twitter.utils.SubjectClueReader;
import de.unidue.langtech.teaching.pp.example.BaselineExample;
import de.unidue.langtech.teaching.pp.example.EvaluatorExample;
import de.unidue.langtech.teaching.pp.example.ReaderExample;

public class ExtendedPipeline
{

    public static void main(String[] args)
        throws Exception
    {
    	/*
    	 * This Pipeline requires some exterior input.
    	 * The subjectDictionary will contain a list of subjects with a positive, neutral or negative annotation.
    	 * It is used in the evaluation module of the pipeline.
    	 */
    	
    	
        SimplePipeline.runPipeline(
                CollectionReaderFactory.createReader(
                        TweetReader.class,
                        TweetReader.PARAM_INPUT_TWEETS, "twitter/resources/twitter_training_complete.txt"
                ),
                AnalysisEngineFactory.createEngineDescription(ArktweetTokenizer.class),
//                AnalysisEngineFactory.createEngineDescription(StanfordSegmenter.class),
//              AnalysisEngineFactory.createEngineDescription(StanfordPosTagger.class, StanfordPosTagger.PARAM_LANGUAGE, "en"),
//                AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),
                
//                AnalysisEngineFactory.createEngineDescription(BaselineExample.class),
                AnalysisEngineFactory.createEngineDescription(OpinionClassifier.class),
                AnalysisEngineFactory.createEngineDescription(EvaluatorExample.class),
//                AnalysisEngineFactory.createEngineDescription(StanfordParser.class),
                AnalysisEngineFactory.createEngineDescription(CasDumpWriter.class)
                
        );
    }
}