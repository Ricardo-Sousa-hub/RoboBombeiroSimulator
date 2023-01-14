package RoboBombeiro;

import cg3d.shapes.Floor;
import com.sun.j3d.audioengines.javasound.JavaSoundMixer;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.URL;

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
    private boolean isPlaying = true;
    private PointSound pSound = new PointSound();

    private BoundingSphere bounds = new BoundingSphere();

    private boolean isRotating = false;
    private RotationInterpolator rotator = null;
    private Transform3D tr = null;

    public void Start() {
        if (!isRunning) {
            isRunning = true;
        }
    }

    public void Stop() {
        if (isRunning) {
            isRunning = false;
        }
    }

    public void playMusic(){
        if(!isPlaying){
            isPlaying = true;
            pSound.setEnable(isPlaying);
        }
    }

    public void stopMusic(){
        if(isPlaying){
            isPlaying = false;
            pSound.setEnable(isPlaying);
        }
    }

    public void changeMusicState(){
        if(isPlaying){
            stopMusic();
        }
        else{
            playMusic();
        }
    }

    private void startRotating(){
        if(!isRotating){
            isRotating = true;
            rotator.setEnable(isRotating);
        }
    }

    private void stopRotating(){
        if(isRotating){
            isRotating = false;
            rotator.setEnable(isRotating);
        }
    }

    private void changeRotationState(){
        if(isRotating){
            stopRotating();
        }
        else{
            startRotating();
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

    public Display() {

        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D cv = new Canvas3D(gc);

        SimpleUniverse su = new SimpleUniverse(cv, 2);
        su.getViewingPlatform().setNominalViewingTransform();

        setLayout(new BorderLayout());
        add(cv, BorderLayout.CENTER);

        BranchGroup bg = createSceneGraph(su.getViewingPlatform().getMultiTransformGroup().getTransformGroup(0));
        bg.compile();

        su.getViewingPlatform().setNominalViewingTransform();

        su.addBranchGraph(bg);

        Transform3D viewTr = new Transform3D();

        viewTr.lookAt(new Point3d(-1, 2, 0.5), new Point3d(0, 0, 0), new Vector3d(0, 1, 0));
        viewTr.invert();

        su.getViewingPlatform().getViewPlatformTransform().setTransform(viewTr);

        OrbitBehavior orbit = new OrbitBehavior(cv);
        orbit.setSchedulingBounds(bounds);

        su.getViewingPlatform().setViewPlatformBehavior(orbit);

        AudioDevice audioDevice = new JavaSoundMixer(su.getViewer().getPhysicalEnvironment());
        audioDevice.initialize();

        JMenuBar menuBar = new JMenuBar();

        JMenu menuOption = new JMenu("Opções");

        JMenuItem menuItem = new JMenuItem("Sair");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuOption.add(menuItem);

        menuItem = new JMenuItem("Ativar/Desativar Som de fundo");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeMusicState();
            }
        });
        menuOption.add(menuItem);

        menuItem = new JMenuItem("Ativar/Desativar Rotação");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeRotationState();
            }
        });
        menuOption.add(menuItem);

        menuBar.add(menuOption);
        setJMenuBar(menuBar);
    }

    private BranchGroup createSceneGraph(TransformGroup tgView) {
        BranchGroup root = new BranchGroup();

        BoundingSphere soundBounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 10.0);

        Transform3D trans = new Transform3D();
        TransformGroup objTg = new TransformGroup(trans);
        objTg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objTg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        root.addChild(objTg);

        URL url = this.getClass().getClassLoader().getResource("music/Bohemian Rhapsody MrChicken cover.wav");
        MediaContainer mc = new MediaContainer(url);

        objTg.addChild(sound(url, mc, pSound, soundBounds));

        floor(root);

        rotate(root, tgView);

        return root;
    }

    private PointSound sound(URL url, MediaContainer mc, PointSound pSound, BoundingSphere soundBounds){
        pSound.setSoundData(mc);
        pSound.setLoop(Sound.INFINITE_LOOPS);
        pSound.setCapability(Sound.ALLOW_ENABLE_WRITE);
        pSound.setInitialGain(1f);
        float[] distances = { 1f, 10f };
        float[] gains = { 1f, 0.001f };
        pSound.setDistanceGain(distances, gains);
        pSound.setSchedulingBounds(soundBounds);
        pSound.setEnable(true);

        return pSound;
    }

    private void floor(BranchGroup root){
        Shape3D floor = new Floor(20, -1f, 2f, new Color3f(Color.WHITE), new Color3f(Color.BLACK), true);
        tr = new Transform3D();
        tr.setTranslation(new Vector3d(-1.1f, -0.5f, 0f));
        TransformGroup tg = new TransformGroup(tr);
        root.addChild(tg);
        tg.addChild(floor);
    }

    private void rotate(BranchGroup root, TransformGroup tgView){
        Alpha alpha = new Alpha(-1, 30000);
        rotator = new RotationInterpolator(alpha, tgView);
        rotator.setEnable(isRotating);
        rotator.setSchedulingBounds(bounds);
        root.addChild(rotator);
    }
}