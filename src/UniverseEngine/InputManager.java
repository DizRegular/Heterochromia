package UniverseEngine;

import RenderObject.BaseObject;
import RenderObject.InputListener;
import java.awt.event.*;
import java.util.ArrayList;
public class InputManager implements KeyListener {
    private static boolean[] eventCurrentFlags = new boolean[255]; 
    private static ArrayList<InputListener> inputListenersObject = new ArrayList<>();
    /** return true if the key is down, uses for managing multiple keys
     * @param key
     * @return boolean
     */
    public static boolean isKeyDown(int key) {
        return InputManager.eventCurrentFlags[key];
    }
    
    public static void registerInputListenerObject(InputListener obj) {
        inputListenersObject.add(obj);
    }
    
    public static void unregisterInputListenerObject(InputListener obj) {
        inputListenersObject.remove(obj);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        char keyPressed = e.getKeyChar();
        InputManager.eventCurrentFlags[keyPressed] = false;
        GameEventListener.handleInput(inputListenersObject);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        char keyPressed = e.getKeyChar();
        InputManager.eventCurrentFlags[keyPressed] = true;
        GameEventListener.handleInput(inputListenersObject);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
