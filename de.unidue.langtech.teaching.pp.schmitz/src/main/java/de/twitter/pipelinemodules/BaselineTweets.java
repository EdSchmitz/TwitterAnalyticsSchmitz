package de.twitter.pipelinemodules;


import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import de.twitter.type.DetectedOpinion;

/**
 * The baseline always identifies positive as the document opinion.
 * For the big testset twitter_training_complete.txt of 11338 tweets it achieves 4215 correct classifications.
 * 
 * @author Schmitz
 *
 */
public class BaselineTweets
    extends JCasAnnotator_ImplBase
{

    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
//        System.out.println("Document is: " + jcas.getDocumentText());
        
//        Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
//        System.out.println("CAS contains " + tokens.size() + " tokens.");
        
        DetectedOpinion opinionAnno = new DetectedOpinion(jcas);
        opinionAnno.setOpinion("positive");
        opinionAnno.addToIndexes();
    }
}