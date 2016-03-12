package de.twitter.test;

import static org.junit.Assert.*;
import org.junit.Test;
import de.twitter.utils.SubjClue;

public class SubjClueTest {

	@Test
	public void testSubjClue() throws Exception {
		//bool:isStrong ; String : word ; bool : isStemmed ; String : polarity ; bool : isSmiley
		SubjClue weakWord = new SubjClue(false, "bad", true, "negative", false);
		SubjClue smiley = new SubjClue(true, ":)", true, "positive", true);
		SubjClue strongWord = new SubjClue(true, "fantastic", true, "positive", false);
		
		//Test weakWord
		{
			assertEquals(weakWord.isStrong(), false);
			assertEquals(weakWord.getWord(), "bad");
			assertEquals(weakWord.isStemmed(), true);
			assertEquals(weakWord.getPriorpolarity(), "negative");
			assertEquals(weakWord.isSmiley(), false);
		}
		
		//Test smiley
		{
			assertEquals(smiley.isStrong(), true);
			assertEquals(smiley.getWord(), ":)");
			assertEquals(smiley.isStemmed(), true);
			assertEquals(smiley.getPriorpolarity(), "positive");
			assertEquals(smiley.isSmiley(), true);
		}
		
		//Test strongWord
		{
			assertEquals(strongWord.isStrong(), true);
			assertEquals(strongWord.getWord(), "fantastic");
			assertEquals(strongWord.isStemmed(), true);
			assertEquals(strongWord.getPriorpolarity(), "positive");
			assertEquals(strongWord.isSmiley(), false);
		}
	}

}
