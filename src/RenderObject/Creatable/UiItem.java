package RenderObject.Creatable;

import RenderObject.BaseObject;
import UniverseEngine.UiManager;
import java.awt.Graphics2D;

abstract public class UiItem extends BaseObject {
    protected int x, y;
    protected int width, height;
    protected boolean visible = true;
    protected int zIndex = 0;

    public UiItem(String name, int x, int y, int width, int height) {
        super(name);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /** subclass implement วิธีวาดเอง */
    abstract public void draw(Graphics2D g);

    public boolean isVisible() { return visible; }
    public void setVisible(boolean v) { this.visible = v; }

    public int getZIndex() { return zIndex; }
    public void setZIndex(int z) { this.zIndex = z; }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    @Override
    public void onCreate() {
        // FIX: import UiManager ถูกต้อง ไม่ใช้ fully qualified name
        UiManager.registerUiItem(this);
    }

    @Override
    public void onDestroy() {
        UiManager.unregisterUiItem(this);
    }

    
}
