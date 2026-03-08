package Main;
import java.awt.GraphicsEnvironment.*;
import java.awt.*;
import javax.swing.*;
import Logic.Main;
import Main.InputManager;
public class Game implements Runnable {

    private final String windowName = "Game";
    private final JFrame gameWindow;
    private final JPanel curtain;
    private final Dimension resolution = new Dimension(1920, 1080);
    private boolean running = false;
    
    private Fetcher fetcher;
    private Renderer renderMachine;
    private GameManipulator gameManipulator;
    private Thread game;
    private Main devAPI;
    
    public Game() {
        gameWindow = new JFrame(windowName);
        curtain = new JPanel();

        curtain.setPreferredSize(resolution);
        curtain.setBackground(Color.CYAN);
        gameWindow.add(curtain);
        gameWindow.addKeyListener(new InputManager());
        
        gameWindow.pack();
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(gameWindow);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void start() {
        running = true;
        fetcher = new Fetcher();
        devAPI = new Main();
        Thread processAPI = new Thread(devAPI);
        renderMachine = new Renderer(fetcher, gameWindow);
        gameManipulator = new GameManipulator(devAPI);
        devAPI.initialize(this);
        game = new Thread(this);
        game.start();
        processAPI.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;

        int ticks = 0;
        int frames = 0;

        long lasTimer = System.currentTimeMillis();
        double deltaTime = 0;

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
                System.out.println(frames + ", " + ticks + ", " + deltaTime);
                ticks = 0;
                frames = 0;
            }
            try {Thread.sleep(2);} catch (Exception e) {e.printStackTrace();}
        }
    }
    
    public JPanel getGameWindow() {
        return this.curtain;
    }
    
    public static void main(String[] args) {
        new Game().start();

    }

}
