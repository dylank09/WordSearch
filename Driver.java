import java.util.*;
public class Driver {
    public static void main(String[] args) {
        WordSearchPuzzle game = null;
        ArrayList<String> userWords = new ArrayList<String>();
        ArrayList<String> findWords = new ArrayList<String>();
        
        //WordSearchPuzzle puzzle = new WordSearchPuzzle(findWords);
        
        if(args.length == 0) {
            game = new WordSearchPuzzle("hello.txt", 10, 2, 7);
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
        game.getPuzzleAsString();
        game.getWordSearchList();
    }
}
