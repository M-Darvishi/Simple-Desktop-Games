package Games;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel(){
    }


    public JButton newBackBtn(JFrame frame , FrameControl panel) {
        JButton backBtn = new JButton("< Back");
        JPanel parentPanel = (JPanel) panel;
        backBtn.addActionListener(e -> {
            Container parent = parentPanel.getParent();
            CardLayout cardLayout = (CardLayout) parent.getLayout();
            cardLayout.show(parent, "Menu");
            frame.setTitle("Simple Desktop Games");
            panel.gameContineu();
        });
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setFocusPainted(false);
        backBtn.setOpaque(false);
        backBtn.setForeground(Color.black);

        return  backBtn;
    }

    public JButton newPlayAgainBtn(JFrame frame , FrameControl panel) {

        JButton playAgainBtn = new JButton("Play Again");
        playAgainBtn.addActionListener(e -> {
            panel.gameContineu();});
        playAgainBtn.setContentAreaFilled(false);
        playAgainBtn.setBorderPainted(false);
        playAgainBtn.setFocusPainted(false);
        playAgainBtn.setOpaque(false);
        playAgainBtn.setForeground(Color.WHITE);
        playAgainBtn.setEnabled(false);

        return playAgainBtn;
    }

}