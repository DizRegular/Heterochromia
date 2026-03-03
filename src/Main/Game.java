package Main;

import java.awt.*;
import javax.swing.*;
import Logic.Main;

public class Game implements Runnable {

    private final String windowName = "Game";
    private final JFrame gameWindow;
    private final Dimension resolution = new Dimension(1920, 1080);
    private boolean running = false;
    
    private Fetcher fetcher;
    private Renderer renderMachine;
    private GameManipulator gameManipulator;
    private Thread game;
    private Thread devAPI;
    
    public Game() {
        gameWindow = new JFrame(windowName);
        JPanel curtain = new JPanel();

        curtain.setPreferredSize(resolution);
        curtain.setBackground(Color.CYAN);
        gameWindow.add(curtain);

        gameWindow.pack();
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void start() {
        running = true;
        fetcher = new Fetcher();
        renderMachine = new Renderer(fetcher, gameWindow);
        gameManipulator = new GameManipulator();
        devAPI = new Thread(new Main());
        game = new Thread(this);
        game.start();
        devAPI.start();
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
    
    public static void main(String[] args) {
        new Game().start();

    }

}
