package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Questions.QuestionsContainer;

public class MainFrame extends JFrame {

   private int width = 720;
   private int height = 480;
   private QuestionsContainer questionsContainer;

   public MainFrame() {
      this.setTitle("Quiz App v1.0");
      this.setSize(width, height);
      this.setLayout(new BorderLayout());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setResizable(false);
      this.setLocationRelativeTo(null);

      JPanel user_panel = new JPanel();
      user_panel.setPreferredSize(new Dimension(100,480));
      user_panel.setBackground(Color.BLUE);

      this.add(user_panel, BorderLayout.WEST);

      JPanel ach_panel = new JPanel();
      ach_panel.setPreferredSize(new Dimension(150,480));
      ach_panel.setBackground(Color.RED);

      this.add(ach_panel, BorderLayout.EAST);

      JPanel title = new JPanel();
      title.setPreferredSize(new Dimension(width,50));
      title.setBackground(Color.green);

      this.add(title, BorderLayout.NORTH);



      questionsContainer = new QuestionsContainer();
      this.add(questionsContainer, BorderLayout.CENTER);

   }


}
