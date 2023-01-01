import javax.swing.*;
import java.awt.*;

public class Display extends Canvas implements Runnable {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String TITLE = "Robo Bombeiro Simulator";
    private Thread thread;
    private boolean isRunning = false;
    private Render render;

    public Display(){
        render = new Render(WIDTH, HEIGHT);
    }

    public void Start(){
        if(!isRunning){
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public void Stop(){
        if(isRunning){
            isRunning = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void tick(){

    }

    private void render(){

    }

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

    @Override
    public void run() {
        while (isRunning){

        }
    }
}