package UniverseEngine;

import RenderObject.Creatable.UiItem;
import java.awt.Graphics2D;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

public class UiManager {
    // ใช้ CopyOnWriteArrayList เพราะ render และ game logic อยู่คนละ thread
    private static CopyOnWriteArrayList<UiItem> uiItems = new CopyOnWriteArrayList<>();

    public static void registerUiItem(UiItem item) {
        uiItems.add(item);
    }

    public static void unregisterUiItem(UiItem item) {
        uiItems.remove(item);
    }

    /** เรียกจาก Renderer.render() หลัง reset AffineTransform แล้ว
     *  วาดทุก UiItem ที่ visible เรียงตาม zIndex */
    public static void drawUI(Graphics2D g) {
        uiItems.stream()
               .filter(UiItem::isVisible)
               .sorted(Comparator.comparingInt(UiItem::getZIndex))
               .forEach(item -> item.draw(g));
    }
}
