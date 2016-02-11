package de.twitter.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/*
 * Based of the input tweets and the clue dictionry this class will create a new .txt file that includes a list of words that are used often, but where there is no suggested value assigned to them in the dictionary
 */

public class UnaccountedWords {

	public static void main(String[] args) {
		try {
			int currentline = 0;
			File file = new File("twitter/resources/tweets1k.txt");
			List<String> lines = FileUtils.readLines(file);
			int length = lines.size();

			List<SubjClue> subjList = SubjectClueReader
					.generateSubjClues("twitter/resources/subjectivity_clues/subjectClues.tff");
			List<UnknownWord> unknownWords = new ArrayList<UnknownWord>();

			/*
			 * Step1: take next tweet (done when all tweets are through)
			 * Step2: take next word (step 1 if no other words remain)
			 * Step3: check if the word is known to the dictionary
			 * 		true-> go to step 2
			 * 		false-> check if the word is known to the list of unknown words
			 * 			true-> increase count
			 * 			false-> create new entry in list of unknown words
			 */
			
			while (currentline < length) {
				System.out.println(currentline + "/" + length+ " lines have been worked");
				String[] parts = lines.get(currentline).split("\t");
				while (parts.length != 4){
					currentline++;
					parts = lines.get(currentline).split("\t");
				}
				String tweet = parts[3].toLowerCase();
				String[] words = tweet.split(" ");
				
				for (String w : words){
					UnknownWord current = new UnknownWord(w.toLowerCase());
					boolean alreadyaccounted = false;
					if (subjList.contains(current)){
						alreadyaccounted = true;
					}
					for (SubjClue sc : subjList){
						if (sc.getWord().toLowerCase().equals(current.getWord())){
							alreadyaccounted = true;
							System.out.println(current.getWord()+" is not included yet");
						}
					}
					if (!alreadyaccounted){
						if (!unknownWords.contains(current)){
							unknownWords.add(current);
						}
						else {
							unknownWords.get(unknownWords.indexOf(current)).incCounter();
						}
					}
					
				}
				
				currentline++;
			}
			
			for (UnknownWord uw : unknownWords){
				System.out.println(uw.getWord() + " is used "+uw.getCounter()+" times and is not accounted for");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
