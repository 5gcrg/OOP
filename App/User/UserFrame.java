package User;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import main.MainFrame;

public class UserFrame extends JFrame implements ActionListener {
    private String name;
    private JTextField nameField;
    private JLabel nameLabel;
    private JButton button;
    private MainFrame mainFrame;
    private User user;

    public UserFrame() {
       
        this.setTitle("Register");
        this.setSize(400, 150);
        this.setResizable(false);
        this.setLayout(null); // Disable default layout manager
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close app on frame close

        // Create and customize the panel

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());
        panel.setBackground(Color.LIGHT_GRAY); // Optional: Set a background color for the panel

        // Create and add the text field
        nameLabel = new JLabel("Name ");
        nameLabel.setBounds(10, 10, 100, 30);

        nameField = new JTextField(15);
        nameField.setFont(new Font("Serif", Font.PLAIN, 20));
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setBounds(50, 10, 280, 30);
        isEmpty();

        button = new JButton("->");
        button.addActionListener(this);
        button.setBounds(330, 10, 50, 30);

        panel.add(nameLabel);
        panel.add(nameField); // Add the text field to the panel
        panel.add(button);

        // Add the panel to the frame
        this.add(panel);

        repaint();

    }

    public void isEmpty() {
        new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                button.setEnabled(!nameField.getText().trim().isEmpty());
            }

        }).start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            this.dispose();
            mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            name = nameField.getText();
            user = new User(name);
            JOptionPane.showMessageDialog(mainFrame, "Welcome " + user.getName());
          
        }

    }
}
