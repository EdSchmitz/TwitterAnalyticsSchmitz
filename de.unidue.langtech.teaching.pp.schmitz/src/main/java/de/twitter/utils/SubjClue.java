package de.twitter.utils;

public class SubjClue {

	/* 
	 * A Subjectivity Clue consists of 4 parts:
	 * 1. the word itself
	 * 2. is this word positive/negative/neutral
	 * 3. is this word considered a "strog" word
	 * 4. is this word already stemmed?
	 */
	private boolean strong;
	private String word;
	private boolean stemmed;
	private String priorpolarity;
	
	public boolean isStrong() {
		return strong;
	}

	public String getWord() {
		return word;
	}

	public boolean isStemmed() {
		return stemmed;
	}

	public String getPriorpolarity() {
		return priorpolarity;
	}


	
	public SubjClue(boolean strong, String w, boolean s, String p){
		this.strong = strong;
		this.word = w;
		this.stemmed = s;
		this.priorpolarity = p;
	}
	
	public String toString(){
		return ("Word is: "+word + "\n it is strong: "+strong+"\n it is stemmed: "+stemmed+"\n it is rated "+priorpolarity);
	}
	
	public boolean equals(Object o){
		if (o instanceof String){
			if (this.word.toLowerCase().equals(o)) return true;
		}
		return false;
	}
}
