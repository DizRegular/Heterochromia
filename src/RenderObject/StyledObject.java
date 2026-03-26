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
    protected String currentAnim = "";
    
    public StyledObject(String name) {
        super(name);
    }
    
    public void setTexture(String imageName) {
        this.imageName = imageName;
    }
    
    public String getTexture() {
        if (!animations.isEmpty() && (animations.get(currentAnim) != null)) {
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
        this.setSize(this.getSize().multiply(textureScale));
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
