import java.util.*;
import java.io.*;
import java.math.*;
import java.lang.*;

public class WordSearchPuzzle {
    private char[][] puzzle;
    private List<String> puzzleWords;
    private double scale;
    
    public WordSearchPuzzle(List<String> userSpecifiedWords) {
        
    }
    
    public WordSearchPuzzle(String wordFile, int wordCount, int shortest, int longest) {
        
    }
    
    private static ArrayList<String> readWordsFromFile(String wordFile, int wordCount, int shortest, int longest) {  //private method to make the arraylist of words from the file
        //make this method to read from a file and then call on this method in the above constructor
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
                System.out.printf("%d  ", k);           //prints out the index numbers for columns
                }
            System.out.printf("\n");
        
            for(int i = 0; i < puzzle.length; i++) {
                System.out.printf("%d ", i);             //prints out the index numbers for rows
                for(j = 0; j < puzzle[0].length; j++) {
                    System.out.printf(" %c ", puzzle[i][j]); //prints each element in the array
                }
                System.out.printf("\n");
            }
         }
    }
    
    public List<String> getWordSearchList() {
        
    }
    
    public char[][] getPuzzleAsGrid() {
        
    }
    
    public String getPuzzleAsString() {
        
    }
    
    public void showWordSearchPuzzle(boolean hide) {
        
    }
    
    public void generateWordSearchPuzzle() {
        
    }
    
    private char randomDirection() {          //method to pick a random direction
        //helper method ye can make if ye want to
    }
    
    private boolean spaceForWord(String word, int row, int col, char direction) {
        //helper method ye can make if ye want to
    }
    
    public static void main(String[] args) {
 
    }

}
