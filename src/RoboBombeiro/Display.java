package RoboBombeiro;

import com.sun.j3d.utils.picking.PickCanvas;

import javax.media.j3d.Appearance;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class Display extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String TITLE = "Robo Bombeiro Simulator";
    private boolean isRunning = false;

    private Canvas3D offScreenCanvas;
    private View view;
    private PickCanvas pc;
    private Canvas3D cv;
    private Appearance lit = new Appearance();

    public void Start(){
        if(!isRunning){
            isRunning = true;

        }
    }

    public void Stop(){
        if(isRunning){
            isRunning = false;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new Display();

        frame.pack();
        frame.setTitle(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }


}