package Main;
import java.awt.GraphicsEnvironment.*;
import java.awt.*;
import javax.swing.*;
import Logic.Main;
import Main.InputManager;
import javax.swing.border.Border;
public class Game implements Runnable {

    private final String windowName = "Game";
    private static JFrame gameWindow;
    private static JPanel curtain;
    private final Dimension resolution = new Dimension(1920, 1080);
    private boolean running = false;
    
    private Fetcher fetcher;
    private Renderer renderMachine;
    private GameManipulator gameManipulator;
    private Thread game;
    private Main devAPI;
    
    private static double deltaTime = 0;
    private static int ticks = 0;
    private static int frames = 0;
    private static double tickPerSec = 0;
    private static double fps = 0;
    
    private Game() {
        gameWindow = new JFrame(windowName);
        curtain = new JPanel();

        curtain.setPreferredSize(resolution);
        curtain.setBackground(Color.CYAN);
        
        JLabel label = new JLabel("Loading...");
        curtain.add(label, BorderLayout.CENTER);
        gameWindow.add(curtain);
        gameWindow.addKeyListener(new InputManager());
        
        gameWindow.pack();
//        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(gameWindow);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void start() {
        running = true;
        fetcher = new Fetcher();
        devAPI = new Main();
        Thread processAPI = new Thread(devAPI);
        renderMachine = new Renderer(fetcher, Game.gameWindow);
        gameManipulator = new GameManipulator(devAPI);
        devAPI.initialize();
        game = new Thread(this);
        game.start();
        processAPI.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;
        long lasTimer = System.currentTimeMillis();
        

        while (running) {
            long now = System.nanoTime();
            deltaTime += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (deltaTime >= 1) {
                ticks++;
                gameManipulator.tick(deltaTime);
                deltaTime -= 1;
            }
            frames++;
            renderMachine.render();

            if (System.currentTimeMillis() - lasTimer >= 1000) {
                lasTimer += 1000;
                Game.tickPerSec = ticks;
                Game.fps = frames;
                ticks = 0;
                frames = 0;
            }
            try {Thread.sleep(2);} catch (Exception e) {e.printStackTrace();}
        }
    }
    
    
    
    public static JPanel getGameWindow() {
        return Game.curtain;
    }
    
    public static double getDeltaTime() {
        return Game.deltaTime;
    }
    
    public static double getFramesPerSecond() {
        return Game.fps;
    }
        
    public static double getTickPerSecond() {
        return Game.tickPerSec;
    }
    
    public static void main(String[] args) {
        new Game().start();

    }

}
