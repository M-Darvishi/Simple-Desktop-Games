package Games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberPanel extends JPanel {
   private String name = "Color Clash";
   String colorNames[]={"Blue","Green","Red","Yellow","Black"};
   Color  colors[]={Color.RED,Color.BLACK,Color.BLUE,Color.green,Color.YELLOW};
   int nameIndex;
   int colorIndex;
   JLabel menuTitle = new JLabel("");
   Random rand=new Random();

   JButton playAgainBtn = new JButton("Play Again");
   JButton backBtn = new JButton("< Back");

   JButton redButton = new JButton("Red");
   JButton yellowButton = new JButton("Yellow");
   JButton blueButton = new JButton("Blue");
   JButton greenButton = new JButton("Green");
   JButton blackButton = new JButton("Black");

   @Override
   public String getName() {
      return name;
   }

   public GuessTheNumberPanel() throws HeadlessException {
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

      playAgainBtn.addActionListener(e -> {
         gameContineu();});
      playAgainBtn.setContentAreaFilled(false);
      playAgainBtn.setBorderPainted(false);
      playAgainBtn.setFocusPainted(false);
      playAgainBtn.setOpaque(false);
      playAgainBtn.setForeground(Color.WHITE);
      playAgainBtn.setEnabled(false);

      backBtn.addActionListener(e -> {
         Container parent = this.getParent();
         CardLayout cardLayout = (CardLayout) parent.getLayout();
         cardLayout.show(parent,"Menu");
         gameContineu();
      });
      backBtn.setContentAreaFilled(false);
      backBtn.setBorderPainted(false);
      backBtn.setFocusPainted(false);
      backBtn.setOpaque(false);
      backBtn.setForeground(Color.black);

      JPanel Options = new JPanel();
      Options.setOpaque(false);
      Options.add(backBtn);
      Options.add(playAgainBtn);
      this.add(Options,BorderLayout.NORTH);

      gameContineu();
   }

   public void gameContineu(){

      resetGame(true);
      this.setBackground(Color.WHITE);
      backBtn.setForeground(Color.black);
      playAgainBtn.setEnabled(false);

      nameIndex= rand.nextInt(5);
      colorIndex= rand.nextInt(5);

      menuTitle.setText(colorNames[nameIndex]);
      menuTitle.setForeground(colors[colorIndex]);
      menuTitle.setFont(new Font("Arial", Font.BOLD, 32));
      menuTitle.setHorizontalAlignment(SwingConstants.CENTER);
      this.add(menuTitle, BorderLayout.CENTER);
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
            menuTitle.setText("Game Over !");
            menuTitle.setForeground(Color.WHITE);
            playAgainBtn.setEnabled(true);
            backBtn.setForeground(Color.WHITE);

            resetGame(false);

            setBackground(Color.RED);
         }
      }
   }
}
