package de.twitter.utils;

public class UnknownWord {

	private String word;
	private int counter;
	
	public UnknownWord(String s){
		this.word = s;
		this.counter = 1;
	}
	
	public String getWord(){
		return word;
	}
	public int getCounter(){
		return counter;
	}
	public void incCounter(){
		counter++;
	}
	
	public boolean equals(Object o){
		if (o instanceof SubjClue){
			return ((SubjClue)o).getWord().equalsIgnoreCase(word);
		}
		if (o instanceof UnknownWord){
			if (o.toString().equals(word)) return true;
		}	
		return false;
	}

}
