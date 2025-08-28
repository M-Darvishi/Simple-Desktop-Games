package Games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberPanel extends GamePanel implements FrameControl {
   private String name = "Color Clash";
   @Override
   public String getName() {
      return name;
   }
   @Override
   public boolean shouldLockFrameSize() {
      return true;
   }
   @Override
   public Dimension frameNewSize() {
      return new Dimension(800, 500);
   }


   private String colorNames[]={"Blue","Green","Red","Yellow","Black"};
   private Color  colors[]={Color.RED,Color.BLACK,Color.BLUE,Color.green,Color.YELLOW};
   int nameIndex;
   int colorIndex;
   private JLabel colorTitle = new JLabel("");
   private Random rand=new Random();

   private JButton playAgainBtn = new JButton("Play Again");
   private JButton backBtn = new JButton("< Back");

   private JButton redButton = new JButton("Red");
   private JButton yellowButton = new JButton("Yellow");
   private JButton blueButton = new JButton("Blue");
   private JButton greenButton = new JButton("Green");
   private JButton blackButton = new JButton("Black");


   public GuessTheNumberPanel(JFrame frame) throws HeadlessException {
      setBackground(Color.WHITE);
      setLayout(new BorderLayout());

      redButton.addActionListener(new ColorButtonListener(Color.RED));
      greenButton.addActionListener(new ColorButtonListener(Color.GREEN));
      blueButton.addActionListener(new ColorButtonListener(Color.BLUE));
      yellowButton.addActionListener(new ColorButtonListener(Color.YELLOW));
      blackButton.addActionListener(new ColorButtonListener(Color.BLACK));

      JPanel Buttons = new JPanel();
      Buttons.add(redButton);
      Buttons.add(greenButton);
      Buttons.add(blueButton);
      Buttons.add(yellowButton);
      Buttons.add(blackButton);
      this.add(Buttons,BorderLayout.SOUTH);

      playAgainBtn = newPlayAgainBtn(frame , this);
      backBtn =  newBackBtn(frame,this);
      JPanel Options = new JPanel();
      Options.setOpaque(false);
      Options.add(backBtn);
      Options.add(playAgainBtn);
      this.add(Options,BorderLayout.NORTH);

      gameContineu();
   }

    @Override
    public void gameContineu() {
        resetGame(true);
        this.setBackground(Color.WHITE);
        backBtn.setForeground(Color.black);
        playAgainBtn.setEnabled(false);

        nameIndex= rand.nextInt(5);
        colorIndex= rand.nextInt(5);

        colorTitle.setText(colorNames[nameIndex]);
        colorTitle.setForeground(colors[colorIndex]);
        colorTitle.setFont(new Font("Arial", Font.BOLD, 32));
        colorTitle.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(colorTitle, BorderLayout.CENTER);

    }
   public void resetGame(boolean buttonState){
      redButton.setEnabled(buttonState);
      yellowButton.setEnabled(buttonState);
      blueButton.setEnabled(buttonState);
      greenButton.setEnabled(buttonState);
      blackButton.setEnabled(buttonState);

   }

   class ColorButtonListener implements ActionListener{
      private Color color;
      public ColorButtonListener(Color color) {
         this.color=color;
      }
      @Override
      public void actionPerformed(ActionEvent e) {
         if (color.equals(colors[colorIndex]))
            gameContineu();
         else{
            colorTitle.setText("Game Over !");
            colorTitle.setForeground(Color.WHITE);
            playAgainBtn.setEnabled(true);
            backBtn.setForeground(Color.WHITE);

            resetGame(false);

            setBackground(Color.RED);
         }
      }
   }
}
