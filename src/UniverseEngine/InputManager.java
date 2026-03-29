package UniverseEngine;

import RenderObject.BaseObject;
import RenderObject.InputListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
public class InputManager implements KeyListener {
    private static HashSet<Integer> eventCurrentFlags = new HashSet<>(); 
    private static ArrayList<InputListener> inputListenersObject = new ArrayList<>();
    /** return true if the key is down, uses for managing multiple keys
     * @param key
     * @return boolean
     */
    public static boolean isKeyDown(int key) {
        return eventCurrentFlags.contains(key);
    }
    
    public static void registerInputListenerObject(InputListener obj) {
        inputListenersObject.add(obj);
    }
    
    public static void unregisterInputListenerObject(InputListener obj) {
        inputListenersObject.remove(obj);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        eventCurrentFlags.remove(keyPressed);
        GameEventListener.handleInput(inputListenersObject);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        eventCurrentFlags.add(keyPressed);
        GameEventListener.handleInput(inputListenersObject);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
