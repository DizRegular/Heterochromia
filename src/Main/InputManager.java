package Main;

import java.awt.event.*;
import Logic.*;
public class InputManager implements KeyListener {
    private static EventList eventCurrentFlag = EventList.Nothing; //This control what event happens
    public static enum EventList { //List of event that can happen
        Nothing, Change
    } 
    
    public static EventList getCurrentEventFlag() {
        return InputManager.eventCurrentFlag;
    }
    
    public static void setCurrentEventFlag(EventList event) {
        InputManager.eventCurrentFlag = event;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        InputManager.setCurrentEventFlag(EventList.Nothing);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        char keyPressed = e.getKeyChar();
        if (keyPressed == 'w') {
            InputManager.setCurrentEventFlag(EventList.Change);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
