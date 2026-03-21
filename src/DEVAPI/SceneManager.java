/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI;

import java.util.Stack;

/**
 *
 * @author tin_sel
 */
public class SceneManager {
    private static Stack<scene> scenes;
    public SceneManager() {
        scenes = new Stack<>();
    }
    public static void setScene(scene s) {
        scenes.clear();
        scenes.push(s);
        update();
    }
    private static void update() {
        if(!scenes.isEmpty()) scenes.peek().update();
    }
}
