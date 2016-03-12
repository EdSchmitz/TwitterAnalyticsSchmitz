package de.twitter.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.resource.ResourceInitializationException;

public class WordChecker {

	/*
	 * This is an amazing class!
	 * Its purpose is to look up a given word in all the twees and determine, if the word might be a clue to the opinion represented in the tweet.
	 * It might come in handy to enrich the quality of input files.
	 */
	
	final static String TESTED_WORD = "pumped";	
	
		
	public static void main(String[] args) throws ResourceInitializationException {

		int asPositive = 0;
		int asNegative = 0;
		int asNeutral = 0;
		
		File inputFile = new File("twitter/resources/twitter_training_complete.txt");
		List<String> lines;
		int currentline;
		
		try{
			lines = FileUtils.readLines(inputFile);
			currentline = 0;
		}
		catch (IOException e){
			throw new ResourceInitializationException();
		}
		
		while (currentline<lines.size()){
			String[] parts = lines.get(currentline).split("\t");
			if (parts.length==1){
				currentline++;
				parts = lines.get(currentline).split("\t");
			}
			if (parts[3].toLowerCase().contains(TESTED_WORD)){
				switch (parts[2]){
				case "positive" : asPositive++; break;
				case "negative" : asNegative++; break;
				case "neutral" : asNeutral++; break;
				}
			}
			currentline++;
		}
		
		System.out.println(TESTED_WORD+" occured "+asPositive+" times in a positive context.");
		System.out.println(TESTED_WORD+" occured "+asNegative+" times in a negative context.");
		System.out.println(TESTED_WORD+" occured "+asNeutral+" times in a neutral context.");
		
		
	}

}
