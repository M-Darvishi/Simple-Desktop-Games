import javax.swing.*;
import java.awt.*;
import Games.*;
import Games.CrossTheStreetPannel;


public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });

    }

    static class MainFrame extends JFrame {
        public MainFrame() throws HeadlessException {
            Toolkit toolkit = Toolkit.getDefaultToolkit(); //
            setSize(toolkit.getScreenSize().width/2,toolkit.getScreenSize().height/2);
       //     setResizable(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //بسته شدن با دکمه خروج
            setLocationByPlatform(true); // لوکیشن انتخابی سیستم
            setTitle("Simple Desktop Games"); // عنوان پنجره
            Image im = new ImageIcon("D:\\JAVA\\icons\\myIcon.png").getImage(); // ساخت آیکون (خودت بکش)
            setIconImage(im); // تنظیم آیکون پنجره

            // ساخت cardLayout و پنل اصلی
            CardLayout layout = new CardLayout();
            JPanel mainPanel = new JPanel(layout);

            // منو و پنل بازی
            MenuPanel menu = new MenuPanel(mainPanel, layout , this);

            // اد کردن پنل اصلی با layoutها
            mainPanel.add(menu, "Menu");
            layout.show(mainPanel, "Menu");
            add(mainPanel);

            //pack();
        }
    }


    static class MenuPanel extends JPanel{
        
        public MenuPanel(JPanel main , CardLayout cardLayout, JFrame frame) throws HeadlessException {
            setLayout(new BorderLayout());
            setBackground(new Color(0,102,51));


            //عنوان منو
            JLabel menuTitle = new JLabel("Games Menu");
            menuTitle.setFont(new Font("Arial", Font.BOLD, 26));
            menuTitle.setHorizontalAlignment(SwingConstants.CENTER);
            menuTitle.setForeground(Color.white);
            this.add(menuTitle, BorderLayout.NORTH);

            // باکس بازی ها
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); //چینش عمودی دکمه ها
            buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
            buttonPanel.add(Box.createVerticalStrut(15));
            buttonPanel.setOpaque(false); //شفافیت بک  گراند

            JPanel centerWraper = new JPanel(new GridBagLayout()); //برای وسط باکس قرار گرفتن
            centerWraper.setOpaque(false);

            // اضافه کردن دکمه های بازی ها
            addGameButton("1. Color Clash",new GuessTheNumberPanel(frame),buttonPanel,main,cardLayout,frame);
            addGameButton("2. Cross The Street", new CrossTheStreetPannel(frame),buttonPanel,main,cardLayout,frame );


            centerWraper.add(buttonPanel);
            this.add(centerWraper,BorderLayout.CENTER);

        }

        public void addGameButton(String gameName,JPanel gamePanel,JPanel buttonPanel, JPanel menu ,CardLayout cardLayout,JFrame frame) {
           JButton gameButton = new JButton(gameName);
           gameButton.setMaximumSize(new Dimension(200,40));
           gameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
           //زیبایی ظاهری
            gameButton.setContentAreaFilled(false);
            gameButton.setBorderPainted(false);
            gameButton.setFocusPainted(false);
            gameButton.setOpaque(false);
            gameButton.setForeground(Color.white);

           gameButton.addActionListener(e -> {
               cardLayout.show(menu,gameName);
               frame.setTitle(gamePanel.getName());
               if(gamePanel instanceof FrameControl){
                   frame.setResizable(((FrameControl) gamePanel).shouldLockFrameSize());
                   frame.setSize(((FrameControl) gamePanel).frameNewSize());
               }
           }); // با فعال شدن دکمه ،از بین پنل های منو ، پنل بازی نامبرده نمایش داده بشود

            // پنل بازی
            menu.add(gamePanel,gameName);
            //اضافه کردن دکمه به پنل دکمه ها
            buttonPanel.add(Box.createHorizontalStrut(15));
            buttonPanel.add(gameButton);

        }

    }

}