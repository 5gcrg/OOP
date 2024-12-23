import Splashscreen.SplashFrame;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SplashFrame splashFrame = new SplashFrame();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                splashFrame.setVisible(true);
            }

        });
    }

}
