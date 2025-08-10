package Games;

import javax.swing.*;
import java.awt.*;

public interface FrameControl {
    boolean shouldLockFrameSize();
    Dimension frameNewSize();
    void  gameContineu();
    default JButton addBackButton(JFrame frame ,FrameControl panel){
        JButton backBtn = new JButton("< Back");
        JPanel parentPanel =(JPanel) panel;
        backBtn.addActionListener(e -> {
            Container parent = parentPanel.getParent();
            CardLayout cardLayout = (CardLayout) parent.getLayout();
            cardLayout.show(parent,"Menu");
            frame.setTitle("Simple Desktop Games");
            panel.gameContineu();
        });
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setFocusPainted(false);
        backBtn.setOpaque(false);
        backBtn.setForeground(Color.black);
        return backBtn;
    }
}


