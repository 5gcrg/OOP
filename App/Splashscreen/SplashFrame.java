package Splashscreen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import User.UserFrame;

public class SplashFrame extends JFrame {
    // set frame size
    private static final int width = 500;
    private static final int height = 300;
    // initialize object
    SplashPanel splash;

    public SplashFrame() {
        this.setSize(width, height);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        splash = new SplashPanel();
        splash.setBounds(0, 0, width, height);
        this.add(splash);

        this.setVisible(true);
        repaint();
        revalidate();

    }

}

class SplashPanel extends JPanel {
    private int width = 500;
    private int height = 300;
    private JProgressBar progressBar;
    private Timer timer;
    private String loadingSTR = "";
    private Image bgImage;
    private UserFrame userFrame;

    SplashPanel() {
        this.setPreferredSize(new Dimension(width, height));
        loadImage();

        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(100, 250, 300, 20);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.GREEN);
        this.add(progressBar);

        start();
        this.setLayout(null);

    }

    public void loadImage() {
        try {
            bgImage = new ImageIcon("App/assets/logo.png").getImage();
        } catch (Exception e) {
            System.out.println("Failed to load Image, " + e.getMessage());
        }
    }

    public void start() {
        timer = new Timer(500, new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count <= 100) {
                    count += 10;
                    progressBar.setValue(count);
                    progressBar.setString(loadingSTR);

                    switch (count) {
                        case 10:
                            loadingSTR = "Starting Modules";
                            break;
                        case 30:
                            loadingSTR = "Loading Modules";
                            break;
                        case 50:
                            loadingSTR = "Checking Updates";
                            break;
                        case 100:
                            loadingSTR = "Loaded Modules, Starting App!";
                            break;
                    }

                }
                // Dispose the panel or frame after reaching 100
                if (count > 100) {
                    timer.stop(); // Stop the timer
                    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(SplashPanel.this);
                    if (topFrame != null) {
                        topFrame.dispose(); // Dispose the JFrame

                        userFrame = new UserFrame();
                        userFrame.setVisible(true);
                    }
                }
            }
        });
        timer.start();

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);

    }
}