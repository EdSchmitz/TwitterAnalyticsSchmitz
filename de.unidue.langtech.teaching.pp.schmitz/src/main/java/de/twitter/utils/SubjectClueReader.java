package de.twitter.utils;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class SubjectClueReader {

	/*
	 * This method will create and return an ArrayList of all the Subjective
	 * Clues given in an input file.
	 */
	public static ArrayList<SubjClue> generateSubjClues(String path) {

		int currentLine;
		int fileLength;

		File inputFile = new File(path);
		List<String> lines;

		try {
			/*
			 * Initializing the file and some required variables
			 */
			lines = FileUtils.readLines(inputFile);
			currentLine = 0;
			fileLength = lines.size();
			ArrayList<SubjClue> resultList = new ArrayList<SubjClue>();

			/*
			 * The following while loop will create a new SubjClue from each
			 * line in lines and add it to the resultList
			 */
			while (currentLine < fileLength) {
				String[] parts = lines.get(currentLine).split(" ");

				/*
				 * strength is given as "type=weaksubj" or "type=strongsubj".
				 * Thus the .contains method can easily determine which is
				 * given.
				 */
				boolean strong = parts[0].contains("strong");

				/*
				 * The word line is the substring of "word1=WORD" the "W" is
				 * at index 6.
				 */
				String word = parts[2].substring(6);

				boolean stemmed = parts[4].contains("y");
				
				try {
					String polarity = parts[5].substring(14);
				}
				catch (java.lang.StringIndexOutOfBoundsException e){
					System.out.println("Error in line: "+currentLine);
				}
				String polarity = parts[5].substring(14);
				
				SubjClue nextSubjClue = new SubjClue(strong, word, stemmed, polarity);
				resultList.add(nextSubjClue);
				currentLine++;
			}
			
			return resultList;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

}
