package G2_MiniGame.WORDLE;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class WordHandler {
    private static WordHandler instance = null;
    private String answer;
    private final ArrayList<String> WordList;
    private final ArrayList<String> possibleAnswer;
    public static WordHandler getInstance(){
        if (instance == null){
            instance = new WordHandler();
        }

        return instance;
    }

    private WordHandler(){
        WordList = new ArrayList<>();
        possibleAnswer = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/G2_MiniGame/TextFiles/words.txt"));
            String word;
            while ((word = br.readLine()) != null){
                WordList.add(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Words file not found.");
        } catch (IOException e) {
            System.out.println("Error in loading \"Words\" file");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/G2_MiniGame/TextFiles/wordleAnswers.txt"));
            String word;
            while ((word = br.readLine()) != null){
                possibleAnswer.add(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\"Wordle Answers\" file not found.");
        } catch (IOException e) {
            System.out.println("Error in loading \"Wordle Answers\" file.");
        }

        WordList.addAll(possibleAnswer);
    }

    public void generateAnswer(){
        answer = possibleAnswer.get(new Random().nextInt(possibleAnswer.size()));
    }

    public String getAnswer(){
        return answer;
    }

    public boolean inWordList(String word){
        return WordList.contains(word.toLowerCase());
    }
}
