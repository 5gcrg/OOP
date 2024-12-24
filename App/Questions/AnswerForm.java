package Questions;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import main.MainFrame;

public class AnswerForm extends JFrame implements ActionListener, checkAnswer {

    private int width = 480 ;
    private int height = 1280 / 2;
    private JButton checkBTN;
    private String answers;
    private int index;

    public AnswerForm(String questions_path, String answers) {
        this.answers = answers;
        this.setTitle("Answer Form");
        this.setSize(new Dimension(width, height));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Main panel to hold all AnswerPanels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));

        loadQuestions(questions_path, mainPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setBlockIncrement(32);
        scrollPane.setPreferredSize(new Dimension(width, height));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        checkBTN = new JButton("Check");

        this.add(scrollPane);
        this.add(checkBTN, BorderLayout.SOUTH);
        checkBTN.addActionListener(this);

    }

    public void loadQuestions(String filename, JPanel mainPanel) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;

                // Parse the question and options
                String question = line;
                String[] options = new String[4];

                for (int i = 0; i < 4; i++) {
                    line = reader.readLine();
                    if (line != null && line.length() > 3) {
                        options[i] = line; // Remove the "A)", "B)", etc.
                    }
                }

                // Add an AnswerPanel for this question
                mainPanel.add(new AnswerPanel(question, options, index));
                index++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MainFrame mainFrame = new MainFrame();

    public void checkAnswer() {

        if (AnswerPanel.isNull()) {
            JOptionPane.showMessageDialog(null, "You still have unanswered Questions!",
                    "No answer", JOptionPane.WARNING_MESSAGE);
                    return;
        }

        String[] user_answers = AnswerPanel.getUserAnswers(); // User's answers
        List<String> answersList = new ArrayList<>(); // Correct answers from file

        try (BufferedReader br = new BufferedReader(new FileReader(answers))) {
            String line;
            while ((line = br.readLine()) != null) {
                answersList.add(line.trim()); // Add each answer to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
            return; // Exit if there's an error reading the file
        }

        // Convert the answers list to an array
        String[] answers = answersList.toArray(new String[0]);

        // Check if both arrays are of the same size
        if (user_answers.length != answers.length) {
            System.out.println("Mismatch in the number of answers!");
            return;
        }

        // Compare answers and calculate the score
        int score = 0;
        for (int i = 0; i < answers.length; i++) {
            if (user_answers[i].equalsIgnoreCase(answers[i])) {
                score++;
            }
        }

        System.out.println("Your score is: " + score + "/" + answers.length);
        int ans = JOptionPane.showConfirmDialog(this, "Your score is: " + score + "/" +
                answers.length, "Score", JOptionPane.OK_OPTION);

        if (ans == JOptionPane.OK_OPTION) {
            score = 0;
            AnswerPanel.resetUserAnswers();
            this.dispose();
            mainFrame.setVisible(true);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkBTN) {
            checkAnswer();

        }

    }

    @Override
    public void check(String choice) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'check'");
    }

}

class AnswerPanel extends JPanel implements ItemListener, checkAnswer {

    private JLabel question;
    private JRadioButton[] options = new JRadioButton[4];
    private ButtonGroup optionsGroup;
    private static String[] userAnswers;
    static int count = 0;
    private int question_index;

    public AnswerPanel(String questionText, String[] choices, int question_index) {
        this.question_index = question_index;
        setLayout(new GridLayout(5, 1));

        // Initialize the question label
        question = new JLabel(questionText);
        add(question);

        // Initialize radio buttons
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton(choices[i]);
            options[i].addItemListener(this); // Register listener here
            add(options[i]); // Add to panel
        }

        // Group the radio buttons
        optionsGroup = new ButtonGroup();
        for (JRadioButton option : options) {
            optionsGroup.add(option);
        }
        if (userAnswers == null) {
            userAnswers = new String[10];
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        for (int i = 0; i < options.length; i++) {
            if (e.getSource() == options[i]) {
                itemAction(i);
            }
        }
    }

    private void itemAction(int i) {
        String selected = "";
        switch (i) {
            case 0:
                selected = "a";
                break;
            case 1:
                selected = "b";
                break;
            case 2:
                selected = "c";
                break;
            case 3:
                selected = "d";
                break;
            default:
                break;
        }
        saveAnswers(selected);
    }

    public void saveAnswers(String answers) {
        userAnswers[question_index] = answers;

    }

    public static boolean isNull() {
        for (int i = 0; i < userAnswers.length; i++) {
            if (userAnswers[i]==null || userAnswers[i].trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }


    public static void resetUserAnswers() {
        if (userAnswers != null) {
            for (int i = 0; i < userAnswers.length; i++) {
                userAnswers[i] = null;
            }
        }
    }
    

    public static String[] getUserAnswers() {
        return userAnswers;
    }

    @Override
    public void check(String choice) {

    }

}
