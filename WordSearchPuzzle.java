import java.util.*;
import java.io.*;
//import java.math.*;
//import java.lang.*;

public class WordSearchPuzzle {
    private char[][] puzzle;
    private List<String> puzzleWords;
    private double scale = 1.75;
    private int[][] answerLocations;
    private char[] answerDirections;
    
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
               this.puzzle = new char[sq][sq];                      //creates the 2d array 
               this.answerLocations = new int[2][n];
               this.answerDirections = new char[n];
               this.puzzleWords = new ArrayList(userSpecifiedWords);
           }
    }
    
    public WordSearchPuzzle(String wordFile, int wordCount, int shortest, int longest) {
        this(readWordsFromFile(wordFile, wordCount, shortest, longest));   //this statement had to be the first statement in the constructor to compile
    }
    
    private static ArrayList<String> readWordsFromFile(String wordFile, int wordCount, int shortest, int longest) {  //private method to make the arraylist of words from the file
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> fileWords = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(wordFile);    //create file reader
            BufferedReader br = new BufferedReader(fr);  //create buffer reader
            String line, word;
            int len, i = 0, r;
            line = br.readLine() ;             //read first line
                while (line != null) { //&& i < wordCount) {         //words in the file must each be on a new line in order to be added to the arraylist
                    len = line.length();                                                            //loop will only iterate wordCount times or until no more words
                    if((len >= shortest && len <= longest)) {
                        fileWords.add(line.toUpperCase());          //only add the word to the arraylist if it's length is within range shortest - longest
                        //System.out.println(line);
                        //i++;
                    }
                    line = br.readLine();
            }
            
            for(; i < wordCount; ) {
                r = (int)(Math.random()*fileWords.size());
                word = fileWords.get(r);
                if(!(words.contains(word))) {
                    words.add(word);
                    i++;
                }
            }
            
            //Collections.shuffle(words);
            br.close();     //close buffer reader
            fr.close();     //close file reader
            return words;   
        }
        catch(IOException x) {  //basically: if any error then return the arraylist from a list generated using this method...
            return readWordsFromFile("default.txt", 10, 2, 8);
            //return null;
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
            System.out.printf("\n");
         }
    }
    
    public List<String> getWordSearchList() {
        return puzzleWords;
    }
    
    public char[][] getPuzzleAsGrid() {
        return puzzle;
    }
    
    public String getPuzzleAsString() {
        String puzzleString = "";
        int col;
        for(int row = 0; row < puzzle.length; row++) {
            for(col = 0; col < puzzle[row].length; col++) {
                puzzleString += puzzle[row][col];
                
            }
            puzzleString += "\n";
        }
        return puzzleString.toString();
        
    }
    
    public void showWordSearchPuzzle(boolean hide) {
        if(hide) display();
        else {
            display();
            for(int i = 0; i < puzzleWords.size(); i++) {
                System.out.printf("\n%s [%d][%d] %c\n", puzzleWords.get(i), 
                                    answerLocations[0][i], answerLocations[1][i], 
                                    answerDirections[i]);
            }
        }
    }
    
    public void generateWordSearchPuzzle() {
        int row, col, len, sq = puzzle.length;
        String word;
        char dir; 
        for(int i = 0; i < puzzleWords.size(); ) {
            word = puzzleWords.get(i).toUpperCase();
            row = (int)(Math.random() * sq);
            col = (int)(Math.random() * sq);
            dir = randomDirection();
            if(spaceForWord(word, row, col, dir)){
                len = word.length();
                answerLocations[0][i] = col;
                answerLocations[1][i] = row;
                switch(dir)  {   
                    case 'U': for(int j = 0; j < len; j++){             
                                puzzle[row-j][col] = word.charAt(j);
                                answerDirections[i] = dir;
                    }
                        break;
                    case 'D': for(int j = 0; j < len; j++){
                                puzzle[row+j][col] = word.charAt(j);
                                answerDirections[i] = dir;
                    }
                        break;
                    case 'R': for(int j = 0; j < len; j++){
                                puzzle[row][col+j] = word.charAt(j);
                                answerDirections[i] = dir;
                    } 
                        break;
                    case 'L': for(int j = 0; j < len; j++){
                                puzzle[row][col-j] = word.charAt(j);
                                answerDirections[i] = dir;
                    }
                        break;
                    default: 
                }
                i++;
            }
        }
        fillUnused();
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
        int count = 0, len = word.length();
        int sq = puzzle.length;
        char ch;
        word = word.toUpperCase();
        switch(direction) {    //this switch case checks if word would fit in the desired position
          case 'U': if (row+1 < len) return false;
          break;
          case 'D': if (row > sq - len) return false;
          break;
          case 'R': if (col > sq - len) return false;
          break;
          case 'L': if (col+1 < len) return false;
          break;
          default: return false;
        }
          
        switch(direction) {    //this switch case checks if there is empty spaces for each letter of the word to go into
          case 'U': for(int i = 0; i < len; i++){               //it also allows for interlocking of words 
                        ch = puzzle[row-i][col];
                        if(ch == '\0' || ch == word.charAt(i)) count++;
                    }
          break;
          case 'D': for(int i = 0; i < len; i++){
                        ch = puzzle[row+i][col];
                        if(ch == '\0' || ch == word.charAt(i)) count++;
                    }
          break;
          case 'R': for(int i = 0; i < len; i++){
                        ch = puzzle[row][col+i];
                        if(ch == '\0' || ch == word.charAt(i)) count++;
                    } 
          break;
          case 'L': for(int i = 0; i < len; i++){
                        ch = puzzle[row][col-i];
                        if(ch == '\0' || ch == word.charAt(i)) count++;
                    }
          break;
          default: return false;
        }
        return count == len;
    }
    
    public static void main(String[] args) {
        
        List<String> al = new ArrayList<String>();
        al.add("add");
        al.add("fade");
        al.add("dylan");
        al.add("hello");
        WordSearchPuzzle ws = new WordSearchPuzzle("wordsfile.txt", 12, 3, 7);
        ws.generateWordSearchPuzzle();
        ws.display();
        ws.showWordSearchPuzzle(false);
    }

}
