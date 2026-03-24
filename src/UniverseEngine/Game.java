package UniverseEngine;
import java.awt.*;
import javax.swing.*;
import DEVAPI.Main;
import java.util.ArrayList;
public class Game implements Runnable {
    private boolean running = false;
    private static ArrayList<JFrame> windowsHolder = new ArrayList<>();
    
    //Hidden Dependencies = { Fetcher }
    private GameManipulator gameManipulator;
    private Thread game;
    private Main devAPI;
    
    private static double deltaTime = 0;
    private static int ticks = 0;
    private static int frames = 0;
    private static double tickPerSec = 0;
    private static double fps = 0;
    
    private Game() {
        
    }

    public void start() {
        running = true;
        Fetcher.initiate();
        devAPI = new Main();
        Thread processAPI = new Thread(devAPI);
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
            RenderManager.render();

            if (System.currentTimeMillis() - lasTimer >= 1000) {
                lasTimer += 1000;
                Game.tickPerSec = ticks;
                Game.fps = frames;
                ticks = 0;
                frames = 0;
            }
            try {Thread.sleep(1);} catch (Exception e) {e.printStackTrace();}
        }
    }
    
    
    
    public static JFrame createNewWindow(String viewPortName) {
        JFrame window = new JFrame(viewPortName);
        Game.windowsHolder.add(window);
        JPanel screenDisplay = new JPanel();
        screenDisplay.setBackground(EngineSettings.LOADING_BACKGROUND_COLOR);
        
        JLabel label = new JLabel("Loading...");
        screenDisplay.add(label, BorderLayout.CENTER);
        window.add(screenDisplay);
        window.addKeyListener(new InputManager());
        window.setResizable(false);
        window.setUndecorated(true);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return window;
    }
    
    public static ArrayList<JFrame> getWindowHolder() {
        return Game.windowsHolder;
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
