package G2_Capstone.WORDLE;

public interface WordleAnswerHandler {

    // 0 - not winning
    // 1 - winning
    // 2 - word not in wordlist
    static int isWinning(Tile[] answerRow){
        Word wordHandler = Word.getInstance();

        char[] userAnswer = new char[5];

        for(int i = 0; i < answerRow.length; i++){
            userAnswer[i] += answerRow[i].getText().charAt(0);
        }

        if (!wordHandler.inWordList(String.valueOf(userAnswer))){
            return 2; // not in wordlist
        }

        String correctAnswer = wordHandler.getAnswer().toUpperCase();

        if (correctAnswer.equals(String.valueOf(userAnswer))){
            for(Tile tile: answerRow){
                tile.correctPlace();
            }

            return 1;
        }

        String lettersleft = correctAnswer;
        for (int i = 0; i < answerRow.length; i++){
            Tile tile = answerRow[i];
            String tileLetter = tile.getText();

            if (correctAnswer.contains(tileLetter)){
                if(correctAnswer.charAt(i) == tileLetter.charAt(0)){
                    tile.correctPlace();
                } else {
                    if (lettersleft.contains(tileLetter)){
                        tile.incorrectPlace();
                    } else {
                        tile.incorrect();
                    }
                }

                lettersleft = lettersleft.replace(tileLetter,"0");
            } else {
                tile.incorrect();
            }
        }

        return 0;
    }
}
