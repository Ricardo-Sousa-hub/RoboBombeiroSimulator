import javax.swing.*;
import java.awt.*;

public class Display extends Canvas {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Robo Bombeiro Simulator";

    public static void main(String[] args) {
        Display simulador = new Display();

        JFrame frame = new JFrame();

        frame.add(simulador);
        frame.pack();
        frame.setTitle(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}