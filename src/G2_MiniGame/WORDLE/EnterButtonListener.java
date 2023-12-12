package G2_MiniGame.WORDLE;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterButtonListener extends AnswerListener implements ActionListener {
    JButton btnEnter;

    public EnterButtonListener(WordleGame wordleFrame, TilePanel wordleTiles, JButton btnEnter){
        super(wordleFrame, wordleTiles);
        this.btnEnter = btnEnter;
        btnEnter.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.pressedEnter();
    }
}
