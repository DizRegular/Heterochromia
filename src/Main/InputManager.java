package Main;

import java.awt.event.*;
import Logic.*;
public class InputManager implements KeyListener {
    private static EventList eventCurrentFlag = EventList.Nothing; //This control what event happens
    public static enum EventList { //List of event that can happen
        Nothing, WalkEast, WalkWest, WalkNorth, WalkSouth
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
        if (keyPressed == 'd') {
            InputManager.setCurrentEventFlag(EventList.WalkEast);
        } else if (keyPressed == 'a') {
            InputManager.setCurrentEventFlag(EventList.WalkWest);
        } else if (keyPressed == 'w') {
            InputManager.setCurrentEventFlag(EventList.WalkNorth);
        } else if (keyPressed == 's') {
            InputManager.setCurrentEventFlag(EventList.WalkSouth);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
