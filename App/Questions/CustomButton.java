package Questions;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {

    private int margin = 5;

    public CustomButton() {
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setMargin(new Insets(5, 5, 5, 5));
        this.setBackground(Color.BLUE);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.WHITE);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill the rounded rectangle with the background color
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 2 * margin, getHeight() - 2 * margin, 30, 30); // Adjust the last two
                                                                                           // parameters for roundness

        // Draw the text over the background
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the border with the foreground color
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 2 * margin - 1, getHeight() - 2 * margin - 1, 30, 30);

        g2.dispose();
    }
}
