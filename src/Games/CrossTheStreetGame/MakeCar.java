package Games.CrossTheStreetGame;

import Games.CrossTheStreetPannel;

public class MakeCar extends Thread {
    private final CrossTheStreetPannel.SquareComponent car;
    private int y;
    private int x;
    private CrossTheStreetPannel.Speed speedEnum;
    CrossTheStreetPannel.Direction direction;

    public CrossTheStreetPannel.Speed getSpeedEnum() {
        return speedEnum;
    }

    public MakeCar(int y , CrossTheStreetPannel.Direction d , CrossTheStreetPannel.Speed s, CrossTheStreetPannel parent) throws InterruptedException {
        car = new CrossTheStreetPannel.SquareComponent(25 , s.getColor());
        this.y = y;
        this.direction = d;
        this.x = (d == CrossTheStreetPannel.Direction.START_FROM_LEFT) ? 0 : 500;
        this.speedEnum = s;
        this.getCarComponent().setRunning(true);
        car.setLocation( x , y);
        parent.add(car);
    }

    public CrossTheStreetPannel.SquareComponent getCarComponent() {
        return car;
    }

    @Override
    public void run() {
        int i;

        if (direction == CrossTheStreetPannel.Direction.START_FROM_LEFT) {
            i = 0;
            while (car.isRunning()) {
                try {
                    Thread.sleep(speedEnum.getSpeed());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                car.setLocation(i, y);
                car.repaint();
                i++;
                if (i > 500)
                    i = 0;
            }
        }
        else {
            i =500;
            while (car.isRunning()) {
                try {
                    Thread.sleep(speedEnum.getSpeed());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                car.setLocation(i, y);
                car.repaint();
                i--;
                if (i < 0)
                    i = 500;
            }
        }


    }
}
