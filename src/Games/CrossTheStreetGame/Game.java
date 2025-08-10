package Games.CrossTheStreetGame;

import Games.CrossTheStreetPannel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends Thread{
    private Frog frog;
    private ArrayList<CrossTheStreetPannel.SquareComponent> carsList;
    private JPanel gameOverPanel;
    private JPanel panel;

    private  int x =175;
    private  int y= 200;
    JLabel message = new JLabel("Game Over !");


    public Game(Frog f , ArrayList<CrossTheStreetPannel.SquareComponent> array , JPanel p){
        frog = f;
        carsList = new ArrayList<>(array);
        panel = p;
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
    }

    public void reset(){
        panel.setBackground(Color.WHITE);
        gameOverPanel.setVisible(false);
        frog.getFrogComponent().setLocation(frog.getStarX(),frog.getStartY());
        frog.getFrogComponent().setColor(new Color(0,65,0));
        frog.getFrogComponent().repaint();
        frog.getFrogComponent().setRunning(true);
        /*
        for (MakeCar c : cars) {
            c.getCarComponent().setColor(c.getSpeedEnum().getColor());
            c.getCarComponent().setRunning(true);
        }
         */

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
