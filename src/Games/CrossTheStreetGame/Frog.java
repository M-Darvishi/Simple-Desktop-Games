package Games.CrossTheStreetGame;

import Games.CrossTheStreetPannel;

import javax.swing.*;
import java.awt.*;

public class Frog extends  Thread{
    private final CrossTheStreetPannel.SquareComponent frog;
    private volatile String direction =null;
    private final int startX = 250;
    private final int startY =420;
    private int x = startX;
    private int y = startY;

    public int getStartY() {
        return startY;
    }
    public int getStarX() {
        return startX;
    }

    public Frog(JPanel parent){
        frog =new CrossTheStreetPannel.SquareComponent(15 , new Color(0,65,0));
        frog.setRunning(true);
        frog.setLocation(startX, startY);
        parent.add(frog);
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }

    public CrossTheStreetPannel.SquareComponent getFrogComponent (){
        return frog;
    }

    public void run(){
        while (frog.isRunning()) {
            if (direction != null) {
                switch (direction){
                    case "UP":
                        y -=15;
                        break;
                    case "DOWN":
                        y +=15;
                        break;
                    case "RIGHT":
                        x +=15;
                        break;
                    case "LEFT":
                        x -=15;
                        break;
                    default:
                        break;
                }
                if (x >500 )
                    x=0;
                if (x <0 )
                    x=500;
                if (y >500 )
                    y=0;
                if (y <0 )
                    y=500;
                frog.setLocation(x, y);
                frog.repaint();
                direction = null;
            }
        }
    }

}