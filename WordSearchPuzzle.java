import java.util.*;
import java.io.*;

public class WordSearchPuzzle {
	private char[][] puzzle;
	private List<String> puzzleWords;
	private double scale = 1.6;
	private int direction;
	
	public WordSearchPuzzle(List<String> userSpecifiedWords) {
		int n = userSpecifiedWords.size();
		String word, biggest = "";
		direction = (int) Math.random() * 4;         // random number will later decide which direction the word will take
		for(int i = 0; i < n; i++) {                 //loop gets the biggest word in the list
			word = userSpecifiedWords.get(i);
			if (biggest.length() <= word.length()) {
				biggest = word;
			}
		}
		if(biggest.length() > n) {
			n = biggest.length();
		}
			
		puzzle = new char[(int) (n * scale)][(int) (n * scale)];  
	}
	
	public WordSearchPuzzle(String wordFile, int wordCount, int shortest, int longest) {
		
	}
	
	public void fillUnused() {
    	int n,col;
    	String alphabet = "abcdefghijklmnopqrstuvwyxz";
        for(int row = 0; row < puzzle.length; row++) {
        	for(col = 0; col< puzzle[0].length; col++) {
        		if(puzzle[row][col] == '\0') {
        			n = (int)(Math.random() * alphabet.length());
        			puzzle[row][col] = alphabet.charAt(n);  
        		}
        	}
        }
    }
	
	public void display() {
    	int j;
    	System.out.print("   ");
    	for(int k = 0; k < puzzle[0].length; k++) {
    		System.out.printf("%d\t ", k);
    	}
    	System.out.printf("\n");
    	
    	for(int i = 0; i < puzzle.length; i++) {
    		System.out.printf("%d ", i);
        	for(j = 0; j < puzzle[0].length; j++) {
        		System.out.printf(" %c\t", puzzle[i][j]);
        	}
        	System.out.printf("\n");
        }
    }
	
	public static void main(String[] args) {
		List<String> al = new ArrayList<String>();
		al.add("add");
		WordSearchPuzzle ws = new WordSearchPuzzle(al);
		ws.fillUnused();
		ws.display();
	}

}
