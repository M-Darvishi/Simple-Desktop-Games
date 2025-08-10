package Games;
import Games.CrossTheStreetGame.Frog;
import Games.CrossTheStreetGame.Game;
import Games.CrossTheStreetGame.MakeCar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CrossTheStreetPannel extends JPanel implements FrameControl {
    private String name = "Cross The Street";
    JFrame frame;
    Frog frog = new Frog(this);
    ArrayList<SquareComponent> carsComponentList = new ArrayList<>();

    // TODO: برای ریست بازی یا تعویض مرحله
    //  کلاس‌های Thread ( Frog و MakeCar) بعد از توقف قابل اجرا مجدد نیستن
    //  بنابراین باید حذف و دوباره ساخته بشن
    //  و همچنین ساخت پنل جدید برای هر مرحله
    @Override
    public void gameContineu() {

    }

    public CrossTheStreetPannel(JFrame f) throws HeadlessException {
        setLayout(null);
        setBackground(Color.white);
        this.frame =f;

        JButton backBtn = addBackButton(frame,this);
        backBtn.setBounds(0,0,80,30);
        this.add(backBtn);

        // todo :اضافه کرئن سیستم مرحله بندی بازی با بارگذاری از فایل ها و یا کلاس

        MakeCar c1 = null;
        MakeCar c2 = null;
        MakeCar c3 = null;
        MakeCar c4 = null;
        try {
            c1 = new MakeCar(100,CrossTheStreetPannel.Direction.START_FROM_LEFT, CrossTheStreetPannel.Speed.FIVE , this);
            carsComponentList.add(c1.getCarComponent());
            c2 = new MakeCar(200,CrossTheStreetPannel.Direction.START_FROM_RIGHT , CrossTheStreetPannel.Speed.TWO, this);
            carsComponentList.add(c2.getCarComponent());
            c3 = new MakeCar(50,CrossTheStreetPannel.Direction.START_FROM_RIGHT, CrossTheStreetPannel.Speed.FOUR, this);
            carsComponentList.add(c3.getCarComponent());
            c4 = new MakeCar(300,CrossTheStreetPannel.Direction.START_FROM_RIGHT, CrossTheStreetPannel.Speed.THREE, this);
            carsComponentList.add(c4.getCarComponent());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        c1.start();
        c2.start();
        c3.start();
        c4.start();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        frog.setDirection("UP");
                        break;
                    case KeyEvent.VK_DOWN:
                        frog.setDirection("DOWN");
                        break;
                    case KeyEvent.VK_RIGHT:
                        frog.setDirection("RIGHT");
                        break;
                    case KeyEvent.VK_LEFT:
                        frog.setDirection("LEFT");
                        break;
                    default:
                        break;
                }
            }
        });
        frog.start();

        setFocusable(true);
        addHierarchyListener( e -> {
            if((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing()){
                requestFocusInWindow();
            }
        });

        Game game=new Game(frog, carsComponentList,this);
        game.start();

    }

    public static class SquareComponent extends JComponent {
        private int size;
        private Color color;
        protected volatile boolean running;
        public SquareComponent(int size, Color color) {
            this.size = size;
            this.color = color;
            setSize(size, size);
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            g.fillRect(0, 0, size, size);
        }
        public void setRunning(boolean b) {
            running = b;
        }
        public void setColor(Color color) {
            this.color = color;
        }

        public boolean isRunning() {
            return running;
        }
    }

    public enum Direction{
        START_FROM_RIGHT,
        START_FROM_LEFT
    }
    public enum Speed{
        TWO(2,Color.BLUE),
        THREE(3,Color.PINK),
        FOUR(4,Color.GREEN),
        FIVE(5,Color.RED);
        private final int speed;
        private final Color color;
        Speed(int speed, Color c) {
            this.speed = speed;
            this.color = c;
        }
        public int getSpeed() {
            return speed;
        }
        public Color getColor() {
            return color;
        }
    }


    @Override
    public String getName() {
        return name;
    }
    @Override
    public boolean shouldLockFrameSize() {
        return false;
    }
    @Override
    public Dimension frameNewSize() {
        return new Dimension(500, 500);
    }
}


