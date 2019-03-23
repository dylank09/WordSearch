import java.util.*;
import java.io.*;
import java.math.*;
import java.lang.*;

public class WordSearchPuzzle {
	private char[][] puzzle;
	private List<String> puzzleWords;
	private double scale = 1.75;
	
	public WordSearchPuzzle(List<String> userSpecifiedWords) {
		
	    int n = userSpecifiedWords.size();
	    if(n < 1) {
	        puzzle = new char[0][0];
	        System.out.println("Error: List is empty");
	       }
	       else {
	           int characterSum = 0, wordLength, sq;
	           for(int i = 0; i < n; i++) {                 //loop sums the number of characters in list 
	               wordLength = userSpecifiedWords.get(i).length();  //wordLength takes the length of the word in the arraylist at position i
	               characterSum += wordLength;              //adds on the length of the word in the arraylist at position i
	           }
	           characterSum *= scale;                          //multiplies sum of characters by the scale factor
	           sq =  (int) Math.ceil(Math.sqrt(characterSum)); //rounds up the square root of the sum of the lengths of the words in the list
	           puzzle = new char[sq][sq];                      //creates the 2d array 
	           
	       }
	   }
	
       	public WordSearchPuzzle(String wordFile, int wordCount, int shortest, int longest) {
        	this(readWordsFromFile(wordFile, wordCount, shortest, longest));   //this statement had to be the first statement in the constructor to compile
	}
	
	private static ArrayList<String> readWordsFromFile(String wordFile, int wordCount, int shortest, int longest) {  //private method to make the arraylist of words from the file
	    ArrayList<String> words = new ArrayList<String>(); 
	    try {
	        FileReader fr = new FileReader(wordFile);    //create file reader
	        BufferedReader br = new BufferedReader(fr);  //create buffer reader
	        String line;
	        int len,i = 0;
                line = br.readLine() ;             //read first line
                while (line != null && i < wordCount) {         //words in the file must each be on a new line in order to be added to the arraylist
                    len = line.length();                                                            //loop will only iterate wordCount times or until no more words
                    if((len >= shortest && len <= longest)) {
                        words.add(line.toUpperCase());          //only add the word to the arraylist if it's length is within range shortest - longest
                        System.out.println(line);
                        i++;
                    }
                    line = br.readLine();
	        }
	        
	        br.close();     //close buffer reader
	        fr.close();     //close file reader
	        return words;   
	    }
	    catch(IOException x) {  //basically: if any error then return null
	    	return null;
	    }
	}
	
	
	public void fillUnused() {
    	   if(puzzle.length != 0) {
		int n,col;
    		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWYXZ";
        	for(int row = 0; row < puzzle.length; row++) {
        		for(col = 0; col< puzzle[0].length; col++) {
        			if(puzzle[row][col] == '\0') {                    //checks if the array element is null
        				n = (int)(Math.random() * alphabet.length()); //random number from 0-25
        				puzzle[row][col] = alphabet.charAt(n);        //puts in a random letter
        			}
        		}
        	}
           }
        }
	
	public void display() {
    	   if(puzzle.length != 0) {
			int j;
    		System.out.print("   ");
    		for(int k = 0; k < puzzle[0].length; k++) {
    			System.out.printf("%d\t ", k);           //prints out the index numbers for columns
                }
    		System.out.printf("\n");
    	
    		for(int i = 0; i < puzzle.length; i++) {
    			System.out.printf("%d ", i);             //prints out the index numbers for rows
        		for(j = 0; j < puzzle[0].length; j++) {
        			System.out.printf(" %c\t", puzzle[i][j]); //prints each element in the array
        		}
        		System.out.printf("\n");
        	}
           }
        }
	
	public List<String> getWordSearchList() {
		return null;
	}
	
	public char[][] getPuzzleAsGrid() {
		return null;
	}
	
	public String getPuzzleAsString() {
		return null;
	}
	
	public void showWordSearchPuzzle(boolean hide) {
		
	}
	
	public void generateWordSearchPuzzle() {
		
	}
	
	private char randomDirection() {          //method to pick a random direction
		int r = (int)(Math.random()*4);
		switch (r) {
		case 0: return 'U';
		case 1: return 'D';
		case 2: return 'R';
		case 3: return 'L';
		default:return '\0';
		}
		
	}
	
	private boolean spaceForWord(String word, int row, int col, char direction) {
		int len = word.length();
		//switch case
		return true;
	}
	
	public static void main(String[] args) {
		
		List<String> al = new ArrayList<String>();
		al.add("add");
		al.add("fade");
		WordSearchPuzzle ws = new WordSearchPuzzle(al);
		ws.fillUnused();
		ws.display();
		System.out.println(ws.randomDirection());
		
	}

}
