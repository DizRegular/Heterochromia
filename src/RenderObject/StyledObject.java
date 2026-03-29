package RenderObject;

import RenderObject.Creatable.Animator;
import RenderObject.Creatable.Vector2D;
import UniverseEngine.Renderer;
import java.util.HashMap;

abstract public class StyledObject extends GameObject {
    protected String imageName;
    protected Vector2D textureSize = new Vector2D(10, 10);
    protected double textureScale = 1;
    protected boolean visibility = true;
    protected HashMap<String, Animator> animations = new HashMap<>();
    protected String currentAnim = "none";
    protected int currPriority = 0;
    
    public StyledObject(String name) {
        super(name);
        animations.put("none", new Animator("placeholder"));
    }
    
    public void setTexture(String imageName) {
        this.imageName = imageName;
    }
    
    public String getTexture() {
        if (!animations.isEmpty() && (animations.get(currentAnim) != null) && (animations.get(currentAnim).isCreate())) {
             return animations.get(currentAnim).getCurrentFrameImage();
        } else {
            return this.imageName;
        }
    }
    
    public boolean getVisibility() {
        return this.visibility;
    }
    
    public void setVisibility(boolean b) {
        this.visibility = b;
    }
    
    public static void setTextureAll(StyledObject[] objs, String imageName) {
        int size = objs.length;
        for (int i =0; i < size; i++) {
            objs[i].setTexture(imageName);
        }
    }
    
    public void getAnimationsName() {
        this.animations.keySet();
    }

    public int getCurrPriority() {
        return currPriority;
    }

    public void setCurrPriority(int currPriority) {
        this.currPriority = currPriority;
    }
    
    public void addAnimator(String animName, Animator animation) {
        animation.parent = this;
        this.animations.put(animName, animation);
    }
    
    public void removeAnimator(String animName, Animator animation) {
        animations.get(currentAnim).setEnabled(false);
        Animator animator = this.animations.remove(animName);
        animator.parent = null;
    }
    
    public Animator getCurrentAnimator() {
        return animations.get(currentAnim);
    }
    
    public void setCurrentAnimator(String animName, int IncomingPriority) {
        if (this.getCurrentAnimator().isLocked() == true) {return;}
        if (animName.equals(this.getCurrentAnimator().getName())) {return;}
        if (currPriority > IncomingPriority) {return;}
        this.getCurrentAnimator().setEnabled(false);
        this.currPriority = IncomingPriority;
        this.currentAnim = animName;
        System.out.println(this.getCurrentAnimator() + "," + this.name + "," + animName);
        this.getCurrentAnimator().setFinished(false);
        this.getCurrentAnimator().setCurrentFrame(0);
        this.getCurrentAnimator().setEnabled(true);
    }
    
    public void setRunAnimator(Boolean b) {
        animations.get(currentAnim).setEnabled(b);
    }
    
    public Vector2D getTextureSize() {
        return this.textureSize;
    }
    
    public void setTextureSize(Vector2D textureSize) {
        this.textureSize = textureSize;
    }
    
    public double getTextureScale() {
        return this.textureScale;
    }
    
    public void setTextureScale(double scale) {
        this.textureScale = scale;
        this.textureSize.multiply(scale);
    }
    
    /** true = flip
     *  false = normal
     * @param flip
     */
    public void flipXTexture(boolean flip) {
        if ((flip == true  && textureSize.getXCoord() > 0)
        || (flip == false  && textureSize.getXCoord() < 0)){
            this.textureSize.setX(textureSize.getXCoord()*-1);
        }
    }
    
    /** true = flip
     *  false = normal
     * @param flip
     */
    public void flipYTexture(boolean flip) {
        if ((flip == true  && textureSize.getYCoord() > 0)
        || (flip == false  && textureSize.getYCoord() < 0)){
            this.textureSize.setY(textureSize.getYCoord()*-1);
        }
    }
    
    public int getDirectionX() {
        if (this.getTextureSize().getXCoord() >= 0) {
            return 1;
        } else {
            return -1;
        }
    }
    
    public int getDirectionY() {
        if (this.getTextureSize().getYCoord() >= 0) {
            return 1;
        } else {
            return -1;
        }
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        this.textureSize = this.getSize();
        Renderer.registerStyledObject(this);
    }
    
    @Override
     public void onDestroy() {
        super.onDestroy();
        Renderer.unregisterStyledObject(this);
    }
}
