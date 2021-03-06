import java.util.*;
import java.io.*;

/***************************
 * authors
 * 
 * Student Names:      ID:
 * Dylan Kearney    18227023
 * Cyiaph McCann    17233453
 * Emmet Browne     18238637
 * Athul Shabu      18256155
 * 
 * Group Members: 4
 ***************************/

public class WordSearchPuzzle {
    private char[][] puzzle;
    private List<String> puzzleWords;    
    private char[] answerDirections;  //will store the direction of puzzle words on entry to the puzzle 2d arrray
    private HashMap<String, List<Integer>> answerPos;
    
    public WordSearchPuzzle(List<String> userSpecifiedWords) {    
        double scale = 1.75;
        boolean empty = false;
        if(userSpecifiedWords == null) empty = true;
        else if(userSpecifiedWords.isEmpty()) empty = true;
        
        if(empty) {
            puzzle = new char[0][0];     //makes 2d array 0x0 if list is empty
            System.out.println("Error: List is empty");
            this.puzzleWords = null;
        }
        else {
            int n = userSpecifiedWords.size();
            int characterSum = 0, wordLength, sq;
            for(int i = 0; i < n; i++) {                 //loop sums the number of characters in list 
                wordLength = userSpecifiedWords.get(i).length();  //wordLength takes the length of the word in the arraylist at position i
                characterSum += wordLength;              //adds on the length of the word in the arraylist at position i
            }
            characterSum *= scale;                          //multiplies sum of characters by the scale factor
            sq =  (int) Math.ceil(Math.sqrt(characterSum)); //rounds up the square root of the sum of the lengths of the words in the list
            this.puzzle = new char[sq][sq];                      
            this.answerDirections = new char[n];
            this.puzzleWords = new ArrayList(userSpecifiedWords);
            this.answerPos = new HashMap<String, List<Integer>>();
        }
    }
    
    public WordSearchPuzzle(String wordFile, int wordCount, int shortest, int longest) {
        this(readWordsFromFile(wordFile, wordCount, shortest, longest));   //this statement had to be the first statement in the constructor to compile
    }
    
    public WordSearchPuzzle() {  
        this(readWordsFromFile("BasicEnglish.txt", 15, 2, 8));  // default file to read from
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
                }
                line = br.readLine();
               }
            
            while(i < wordCount) {
                r = (int)(Math.random()*fileWords.size());
                word = fileWords.get(r);
                if(!(words.contains(word))) {
                    words.add(word);
                    i++;
                }
            }
            
            br.close();     //close buffer reader
            fr.close();     //close file reader
            return words;   //return words randomly picked from file
        }
        catch(IOException x) {  //if any error then return the arraylist from a list generated using this method...
            return null;
            }
    }
    
    
    private void fillUnused() {   //helper method to fill the array with random letters of the alphabet
         if(puzzle.length != 0) {   //make sure puzzle is at least 1x1
            int n,col;
            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWYXZ";
            for(int row = 0; row < puzzle.length; row++) {    //traverse the 2d array
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
         if(puzzle.length != 0) {   //make sure puzzle is at least 1x1
            int j;
            System.out.print("   ");
            for(int k = 0; k < puzzle[0].length; k++) {        
                if(k < 10) {
                   System.out.printf("%d  ", k);             //prints out the index numbers for columns
               }
               else {
                   System.out.printf("%d ", k);
               }
            }
            System.out.printf("\n");
        
            for(int i = 0; i < puzzle.length; i++) {
               if(i < 10) {
                   System.out.printf("%d ", i);             //prints out the index numbers for rows
               }
               else {
                   System.out.printf("%d", i);
               }
               for(j = 0; j < puzzle[i].length; j++) {
                   System.out.printf(" %c ", puzzle[i][j]); //loop prints each element in the row and moves to next row
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
        String puzzleString = "";  //start with empty string 
        int col;
        for(int row = 0; row < puzzle.length; row++) {
            for(col = 0; col < puzzle[row].length; col++) {
                puzzleString += puzzle[row][col];   //loop adds on each element of each row of the 2d array
                
            }
            puzzleString += "\n";   // \n at the end of each row
        }
        return puzzleString.toString();  
        
    }
    
    public void showWordSearchPuzzle(boolean hide) {
        if(puzzle.length != 0) {
            if(!hide) {   //if hide is false
                String word;
                display();  //display the puzzle
                for(int i = 0; i < puzzleWords.size(); i++) {
                    word = puzzleWords.get(i);
                    System.out.printf("\n%s [%d][%d] %c\n", word, 
                                        answerPos.get(word).get(0), answerPos.get(word).get(1),
                                        answerDirections[i]);  //show each word and its answer position and direction
                }
            }
            else display();    //if hide is true, display  
        }
    }
    
    public void generateWordSearchPuzzle() {
        if(puzzle.length != 0) {   
            int row, col, len, sq = puzzle.length;  //using variable sq to store the dimension of the 2d array
            String word;
            char dir; 
            for(int i = 0; i < puzzleWords.size(); ) {
                word = puzzleWords.get(i).toUpperCase();  //make word uppercase
                row = (int)(Math.random() * sq);  //random row
                col = (int)(Math.random() * sq);  //random column
                dir = randomDirection();          //random direction
                if(spaceForWord(word, row, col, dir)){
                    len = word.length();  
                    ArrayList<Integer> al = new ArrayList<Integer>(2);
                    al.add(col);
                    al.add(row);
                    answerPos.put(word, al);
                    switch(dir) {   
                        case 'U': for(int j = 0; j < len; j++){             
                                    puzzle[row-j][col] = word.charAt(j);  //puts in character of the word
                                    answerDirections[i] = dir;  //fills array with the direction the word is placed
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
            fillUnused();  //after adding all words to the 2d arry, fill empty spaces with random letters 
    
        }
    }
    
    private char randomDirection() {          //method to pick a random direction
        int r = (int)(Math.random()*4);       //r will have a value between 0 and 3
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
        int sq = puzzle.length;                     //again sq takes the dimennsion of the 2d array
        char ch;
        word = word.toUpperCase(); 
        switch(direction) {                         //this switch case checks if word would fit (length wise) in the desired position and direction
          case 'U': if (row+1 < len) return false;  //return false if the word wont fit in that position, 
          break;                                    //otherwise exit switch case
          case 'D': if (row > sq - len) return false;
          break;
          case 'R': if (col > sq - len) return false;
          break;
          case 'L': if (col+1 < len) return false;
          break;
          default: return false;
        }
          
        switch(direction) {    //this switch case checks if there is empty spaces for each letter of the word to go into
          case 'U': for(int i = 0; i < len; i++){                
                        ch = puzzle[row-i][col];
                        if(ch == '\0' || ch == word.charAt(i)) count++;  //this line only adds one to count if ch is null or the same letter as the word at that index
                    }
          break;
          case 'D': for(int i = 0; i < len; i++){
                        ch = puzzle[row+i][col];
                        if(ch == '\0' || ch == word.charAt(i)) count++;
                    }
          break;
          case 'R': for(int i = 0; i < len; i++) {
                        ch = puzzle[row][col+i];
                        if(ch == '\0' || ch == word.charAt(i)) count++;
                    } 
          break;
          case 'L': for(int i = 0; i < len; i++) {
                        ch = puzzle[row][col-i];
                        if(ch == '\0' || ch == word.charAt(i)) count++;
                    }
          break;
          default: return false;
        }
        return count == len;
    }
}
