package Games.CrossTheStreetGame;

import Games.CrossTheStreetPannel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends Thread{
    private Frog frog;
    private ArrayList<CrossTheStreetPannel.SquareComponent> carsList;
    private JPanel gameOverPanel;
    private CrossTheStreetPannel panel;

    private  int x =175;
    private  int y= 200;
    private JLabel message = new JLabel("Game Over !");

    public JLabel getMessage() {
        return message;
    }

    public Game(Frog f , ArrayList<CrossTheStreetPannel.SquareComponent> array , CrossTheStreetPannel p ){
        frog = f;
        carsList = new ArrayList<>(array);
        panel = p;
    }

    public Frog getFrog() {
        return frog;
    }

    private void gameOverSet(){
        for (CrossTheStreetPannel.SquareComponent c : carsList) {
            c.setColor(Color.DARK_GRAY);
        }
        panel.setBackground(Color.gray);
        frog.getFrogComponent().setColor(Color.DARK_GRAY);
        message.setForeground(Color.WHITE);
        message.setBackground(new Color(255,0,0,130));
        message.setOpaque(true);
        message.setBounds(x,y,150,40);
        message.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(message);
        panel.revalidate();
        panel.repaint();
        panel.setComponentZOrder(message,0);
        message.setVisible(true);
        panel.getPlayAgainBtn().setForeground(Color.BLACK);
        panel.getPlayAgainBtn().setEnabled(true);
    }

    @Override
    public void run() {
        while (frog.getFrogComponent().isRunning()){
            Rectangle frogBounds = frog.getFrogComponent().getBounds();
            for (CrossTheStreetPannel.SquareComponent c : carsList) {
                if (frogBounds.intersects(c.getBounds())) {
                    c.setRunning(false);
                    frog.getFrogComponent().setRunning(false);
                    gameOverSet();
                }
            }
        }
    }
}
