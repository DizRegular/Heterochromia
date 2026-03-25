package RenderObject;

import UniverseEngine.Renderer;

abstract public class StyledObject extends GameObject {
    protected String imageName;
    protected boolean visibility = true;

    public StyledObject(String name) {
        super(name);
    }
    
    public void setTexture(String imageName) {
        this.imageName = imageName;
    }
    
    public String getTexture() {
        return this.imageName;
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
    
    @Override
    public void onCreate() {
        super.onCreate();
        Renderer.registerStyledObject(this);
    }
    
    @Override
     public void onDestroy() {
        super.onDestroy();
        Renderer.unregisterStyledObject(this);
    }
}
