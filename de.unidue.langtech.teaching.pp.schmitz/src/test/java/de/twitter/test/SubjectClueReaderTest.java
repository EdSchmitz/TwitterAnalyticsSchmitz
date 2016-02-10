package de.twitter.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.twitter.reader.SubjClue;
import de.twitter.reader.SubjectClueReader;

public class SubjectClueReaderTest {

	@Test
	public void testSubjectClueReader() {

		/*
		 * The "cluesShort.tff" File is a small portion of the complete set
		 * (subjectClues.tff). It contains 9 subjective ratings one line each.
		 * 
		 * Example line: type=weaksubj len=1 word1=abandon pos1=verb stemmed1=y
		 * priorpolarity=negative
		 * 
		 * contents: 1. type (strong/weak), 2. length (how many words belong to
		 * this entry?), 3. word (which word is rated?), 4. stemmed
		 * (true/false), 5. polarity (negative/neutral/positive)
		 * (9 Lines in total)
		 * 
		 */

		List<SubjClue> shortList = SubjectClueReader.generateSubjClues("twitter/resources/subjectivity_clues/cluesShort.tff");
		
		
		/*
		 * Here all the read words are written on console, for manual check
		 */
		Iterator i = shortList.iterator();
		while (i.hasNext()){
			System.out.println(i.next()+"\n");
		}
		
		/*
		 * Here is the automated check
		 * The testset is as follows:
		 * type=weaksubj len=1 word1=abandoned pos1=adj stemmed1=n priorpolarity=negative
		 * type=weaksubj len=1 word1=abandonment pos1=noun stemmed1=n priorpolarity=negative
		 * type=weaksubj len=1 word1=abandon pos1=verb stemmed1=y priorpolarity=negative
		 * type=strongsubj len=1 word1=abase pos1=verb stemmed1=y priorpolarity=negative
		 * type=strongsubj len=1 word1=abasement pos1=anypos stemmed1=y priorpolarity=negative
		 * type=strongsubj len=1 word1=abash pos1=verb stemmed1=y priorpolarity=negative
		 * type=weaksubj len=1 word1=abate pos1=verb stemmed1=y priorpolarity=negative
		 * type=weaksubj len=1 word1=abdicate pos1=verb stemmed1=y priorpolarity=negative
		 * type=strongsubj len=1 word1=aberration pos1=adj stemmed1=n priorpolarity=negative
		 * 
		 * Will add some positive examples at a later time...
		 */
		
		SubjClue entry1 = shortList.get(0);
		SubjClue entry2 = shortList.get(1);
		SubjClue entry3 = shortList.get(2);
		SubjClue entry4 = shortList.get(3);
		SubjClue entry5 = shortList.get(4);
		SubjClue entry6 = shortList.get(5);
		SubjClue entry7 = shortList.get(6);
		SubjClue entry8 = shortList.get(7);
		SubjClue entry9 = shortList.get(8);
		
		
		assertEquals(false, entry1.isStrong());
		assertEquals(false, entry1.isStemmed());
		assertEquals("abandoned", entry1.getWord());
		assertEquals("negative", entry1.getPriorpolarity());
		
		assertEquals(false, entry2.isStrong());
		assertEquals(false, entry2.isStemmed());
		assertEquals("abandonment", entry2.getWord());
		assertEquals("negative", entry2.getPriorpolarity());
		
		assertEquals(false, entry3.isStrong());
		assertEquals(true, entry3.isStemmed());
		assertEquals("abandon", entry3.getWord());
		assertEquals("negative", entry3.getPriorpolarity());
		
		assertEquals(true, entry4.isStrong());
		assertEquals(true, entry4.isStemmed());
		assertEquals("abase", entry4.getWord());
		assertEquals("negative", entry4.getPriorpolarity());
		
		assertEquals(true, entry5.isStrong());
		assertEquals(true, entry5.isStemmed());
		assertEquals("abasement", entry5.getWord());
		assertEquals("negative", entry5.getPriorpolarity());
		
		assertEquals(true, entry6.isStrong());
		assertEquals(true, entry6.isStemmed());
		assertEquals("abash", entry6.getWord());
		assertEquals("negative", entry6.getPriorpolarity());
		
		assertEquals(false, entry7.isStrong());
		assertEquals(true, entry7.isStemmed());
		assertEquals("abate", entry7.getWord());
		assertEquals("negative", entry7.getPriorpolarity());
		
		assertEquals(false, entry8.isStrong());
		assertEquals(true, entry8.isStemmed());
		assertEquals("abdicate", entry8.getWord());
		assertEquals("negative", entry8.getPriorpolarity());
		
		assertEquals(true, entry9.isStrong());
		assertEquals(false, entry9.isStemmed());
		assertEquals("aberration", entry9.getWord());
		assertEquals("negative", entry9.getPriorpolarity());
		
		
	}

}
