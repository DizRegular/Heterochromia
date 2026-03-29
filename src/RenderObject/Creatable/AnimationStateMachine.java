package RenderObject.Creatable;

import RenderObject.Addon.Scriptable;
import RenderObject.BaseObject;
import java.util.HashMap;

public class AnimationStateMachine extends BaseObject implements Scriptable {
    private int currPriorityLevel = 0;
    protected HashMap<String, Animator> animations = new HashMap<>();
    protected HashMap<String, Integer>translator = new HashMap<>();
    protected String currentAnim = "";
    
    public AnimationStateMachine(String name) {
        super(name);
    }

    public int getCurrPriorityLeve() {
        return currPriorityLevel;
    }

    public void setCurrPriorityLeve(int currPriorityLevel) {
        this.currPriorityLevel = currPriorityLevel;
    }

    public void getAnimationsName() {
        this.animations.keySet();
    }
    
    public void addAnimator(String animName, Animator animation) {
        this.animations.put(animName, animation);
    }
    
    public void removeAnimator(String animName, Animator animation) {
        animations.get(currentAnim).setEnabled(false);
        this.animations.remove(animName);
    }
    
    public Animator getCurrentAnimator() {
        return animations.get(currentAnim);
    }
    
    public void setCurrentAnimator(String animName) {
        this.currentAnim = animName;
    }
    
    public void setRunAnimator(Boolean b) {
        animations.get(currentAnim).setEnabled(b);
    }
    
    public void setAnimationTo(String animName) {
        int priorityRequest = translator.get(animName);
        if (priorityRequest >= currPriorityLevel) {
            this.setCurrentAnimator(name);
        }
    }
    
    @Override
    public void process(double deltaTime) {
        if (animations.isEmpty()) {return;}
        
        
    }
    
    
    
}
