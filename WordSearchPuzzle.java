import java.util.*;

/*
 * authors
 * Dylan Kearney 18227023
 * Cyiaph McCann 17233453
 * Emmet Browne 18238637
 * Athul Shabu 18256155
 */

public class Driver {
    public static void main(String[] args) {
        WordSearchPuzzle game = null;
        ArrayList<String> userWords = new ArrayList<String>();
        ArrayList<String> findWords = new ArrayList<String>();
        
        
        //The following 5 lines of code should print an error saying that
        //the list is empty when the list passed in is either empty or null
        ArrayList<String> words = new ArrayList<String>();  //try to make a puzzle with an empty ArrayList
        WordSearchPuzzle emptyPuzzle = new WordSearchPuzzle(words);
        
        ArrayList<String> words1 = null;  //try make a puzzle with null ArrayList
        WordSearchPuzzle emptyPuzzle1 = new WordSearchPuzzle(words1);
        
        if(args.length == 0) {
            game = new WordSearchPuzzle("BasicEnglish.txt", 10, 2, 7);
        }
        else {
            for(String s : args) {
                userWords.add(s);
            }
            game = new WordSearchPuzzle(userWords);
        }
        
        game.display();
        game.generateWordSearchPuzzle();
        game.display();
        game.showWordSearchPuzzle(false);
        game.getPuzzleAsGrid();
        System.out.println(game.getPuzzleAsString());
        game.getWordSearchList();
        
        WordSearchPuzzle game1 = new WordSearchPuzzle();
        System.out.println(game1.getWordSearchList().toString());
        game1.generateWordSearchPuzzle();
        game1.display();
        
        WordSearchPuzzle game2 = new WordSearchPuzzle("wordsfile.txt", 10, 2, 8);
        System.out.println(game2.getWordSearchList().toString());
        game2.generateWordSearchPuzzle();
        game2.display();
        
        
    }
}
