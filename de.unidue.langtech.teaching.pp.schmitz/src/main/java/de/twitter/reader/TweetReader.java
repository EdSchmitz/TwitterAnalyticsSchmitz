package de.twitter.reader;



import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import de.twitter.type.GoldOpinion;
import de.unidue.langtech.teaching.pp.type.GoldLanguage;

/**
 * @author Schmitz
 *
 */
public class TweetReader
    extends JCasCollectionReader_ImplBase
{

    /**
     * Input file
     */
    public static final String PARAM_INPUT_TWEETS = "InputFile";
    @ConfigurationParameter(name = PARAM_INPUT_TWEETS, mandatory = true)
    private File inputFile;    
    
    private List<String> lines;
    private int currentLine;
    
    /* 
     * initializes the reader
     */
    @Override
    public void initialize(UimaContext context)
        throws ResourceInitializationException
    {
        super.initialize(context);
        
        try {
           lines = FileUtils.readLines(inputFile);
           currentLine = 0;
        }
        catch (IOException e) {
            throw new ResourceInitializationException(e);
        }
    }
    
    
    /* 
     * true, if there is a next document, false otherwise
     */
    public boolean hasNext()
        throws IOException, CollectionException
    {
        return currentLine < lines.size();
    }
    
    
    /* 
     * feeds the next document into the pipeline
     */
    @Override
    public void getNext(JCas jcas)
        throws IOException, CollectionException
    {
        // split line into gold standard rating and actual text
        String[] parts = lines.get(currentLine).split("\t");
        
        /* Checking the line format. Should be: TWEET-ID <<TAB>> ANOTHER-NUMBER <<TAB>> GOLD-RATING <<TAB>> TWEET-TEXT
         * may be followed by an empty line (optional input format)
        */
        
        if (parts.length == 1){
        	currentLine++;
        	parts = lines.get(currentLine).split("\t");
        }
        if (parts.length != 4) {
        	System.out.println("SIZE IS: "+parts.length+" SHOULD BE: 4" + "\n TEXT STARTS WITH \"" + parts[0] +"\"");
            throw new IOException("Wrong line format: " + lines.get(currentLine));
        }
        
        // add gold standard value of opinion as annotation
        GoldOpinion goldOpinion = new GoldOpinion(jcas);
        goldOpinion.setOpinion(parts[2]);
        goldOpinion.addToIndexes();
        
        // add actual text of the document
        jcas.setDocumentText(parts[3]);
        
        // set document language to english
        jcas.setDocumentLanguage("en");
        
        currentLine++;
    }

    
    /* 
     * informs the pipeline about the current progress
     */
    public Progress[] getProgress()
    {
        return new Progress[] { new ProgressImpl(currentLine, lines.size(), "lines") };
    }
}