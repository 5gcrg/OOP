package Questions;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class QuestionsContainer extends JPanel implements ActionListener, checkAnswer {

    private int size = 12;
    private JButton[] questions_Buttons = new JButton[size];

    public QuestionsContainer() {
        this.setLayout(new GridLayout(4, 3));

        loadButtons();

    }

    public void loadButtons() {

        for (int i = 0; i < 10; i++) {
            questions_Buttons[i] = new CustomButton();
            questions_Buttons[i].setBackground(Color.WHITE);
            questions_Buttons[i].setText(String.valueOf(i + 1));
            questions_Buttons[i].addActionListener(this);
            this.add(questions_Buttons[i]);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < size; i++) {
            if (e.getSource() == questions_Buttons[i]) {
                buttonAction(i + 1);
            }
        }

    }

    @Override
    public void check(String choice) {

    }


    // private AnswersContainer answersContainer;
    // @Override
    // public void loadAnswer(String[] answer) {
       
    //     String filename = "App/assets/answer.txt";

    //     try {
    //         BufferedReader br = new BufferedReader(new FileReader(filename));
    //         String line;
    //         String[] answers = new String[10];
    //         while ((line = br.readLine()) != null) {
    //             answers = line.split("\n");
    //             for (String ans : answers) {
    //                 System.out.println(ans);
                    
    //             }
    //             answersContainer = new AnswersContainer(answers);
    //         }
    //         br.close();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    // }

    private void buttonAction(int i) {
        switch (i) {
            case 1:
                System.out.println(1);
                AnswerForm form = new AnswerForm();
                form.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(QuestionsContainer.this);
                frame.setVisible(false);
                break;
            case 2:
                System.out.println(2);
                break;
            case 3:
                System.out.println(3);
                break;
            case 4:
                System.out.println(4);
                break;
            case 5:
                System.out.println(5);
                break;
            case 6:
                System.out.println(6);
                break;
            case 7:
                System.out.println(7);
                break;
            case 8:
                System.out.println(8);
                break;
            case 9:
                System.out.println(9);
                break;
            case 10:
                System.out.println(10);
                break;
            default:
                break;
        }
    }

}
