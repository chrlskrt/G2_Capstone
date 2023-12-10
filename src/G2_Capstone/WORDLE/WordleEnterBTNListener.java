package G2_Capstone.WORDLE;

import G2_Capstone.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordleEnterBTNListener extends WordleAnswerListener implements ActionListener {
    JButton btnEnter;

    public WordleEnterBTNListener(WordleGame wordleFrame, TilePanel wordleTiles, JButton btnEnter, Player player){
        super(wordleFrame, wordleTiles, player);
        this.btnEnter = btnEnter;
        btnEnter.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.pressedEnter();
    }
}
