package Main;

import java.awt.event.*;
import Logic.*;
public class InputManager implements KeyListener {
    private static boolean[] eventCurrentFlags = new boolean[255]; 
    /** return true if the key is down, uses for managing multiple keys
     * @param key
     * @return boolean
     */
    public static boolean isKeyDown(int key) {
        return InputManager.eventCurrentFlags[key];
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        char keyPressed = e.getKeyChar();
        InputManager.eventCurrentFlags[keyPressed] = false;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        char keyPressed = e.getKeyChar();
        InputManager.eventCurrentFlags[keyPressed] = true;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
