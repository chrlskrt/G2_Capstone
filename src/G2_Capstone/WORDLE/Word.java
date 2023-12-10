package G2_Capstone.WORDLE;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Word {
    private static Word instance = null;
    private String answer;
    private final ArrayList<String> WordList;
    private final ArrayList<String> possibleAnswer;
    public static Word getInstance(){
        if (instance == null){
            instance = new Word();
        }

        return instance;
    }

    private Word(){
        WordList = new ArrayList<>();
        possibleAnswer = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/G2_Capstone/TextFiles/words.txt"));
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
            BufferedReader br = new BufferedReader(new FileReader("src/G2_Capstone/TextFiles/wordleAnswers.txt"));
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
