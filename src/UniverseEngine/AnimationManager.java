package UniverseEngine;

import RenderObject.Creatable.Animator;
import java.util.ArrayList;

public class AnimationManager {
    private static ArrayList<Animator> animators = new ArrayList<>();
    
    public static void registerStyledObject(Animator animator) {
        AnimationManager.animators.add(animator);
    }
    
    public static void unregisterStyledObject(Animator animator) {
        AnimationManager.animators.remove(animator);
    }
    
    public static void animateTime(double deltaTime) {
        for (Animator anim : animators) {
            if (anim.isEnabled() == true) {
                anim.animate(deltaTime);
            }
        }
    }
    
}
