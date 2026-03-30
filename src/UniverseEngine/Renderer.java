package UniverseEngine;
import RenderObject.Creatable.Camera;
import RenderObject.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JFrame;

public class Renderer {
    /** Render everything that this game is meant to show to the player */
    private static final CopyOnWriteArrayList<StyledObject> texturedObject = new CopyOnWriteArrayList<>();

    public static void render(Camera camera, JFrame viewPort) {
        BufferStrategy bs = camera.getWindow().getBufferStrategy();
        if (bs == null) {
            camera.getWindow().createBufferStrategy(3);
            return;
        }
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        
        g.fillRect(0, 0, (int) camera.getSize().getXCoord(), (int) camera.getSize().getYCoord());
        g.drawImage(GameUniverse.getBackground(), 0, 0,
                (int) camera.getSize().getXCoord(), (int) camera.getSize().getYCoord(), null);

        double zoomFactor = camera.getZoomFactor();
        double XCenter = viewPort.getSize().getWidth() / 2;
        double YCenter = viewPort.getSize().getHeight() / 2;
        AffineTransform transform = g.getTransform();
        g.translate(XCenter, YCenter);
        g.scale(zoomFactor, zoomFactor);
        g.translate(-XCenter, -YCenter);

        for (BaseObject object : texturedObject) {
            if (object instanceof StyledObject styled && styled.getVisibility()) {
                g.drawImage(GameUniverse.fetchImage(styled.getTexture()),
                        (int) styled.getPosition().getXCoord() + (int) styled.getSize().getXCoord() / 2 - (int) styled.getTextureSize().getXCoord() / 2 - (int) camera.getPosition().getXCoord(),
                        (int) styled.getPosition().getYCoord() + (int) styled.getSize().getYCoord() / 2 - (int) styled.getTextureSize().getYCoord() / 2 - (int) camera.getPosition().getYCoord(),
                        (int) styled.getTextureSize().getXCoord(),
                        (int) styled.getTextureSize().getYCoord(),
                        null);
                if (styled instanceof CollisionObject cobj && EngineSettings.SHOW_HITBOXES) {
                    g.drawRect(
                            (int) cobj.getBounds().getMinX() - (int) camera.getPosition().getXCoord(),
                            (int) cobj.getBounds().getMinY() - (int) camera.getPosition().getYCoord(),
                            (int) cobj.getBounds().getWidth(),
                            (int) cobj.getBounds().getHeight());
                }
            }
        }

        // Reset transform ก่อนวาด UI เพื่อให้ UI ไม่ถูก zoom/เลื่อนตาม camera
        g.setTransform(transform);

        // FIX: เพิ่ม UiManager.drawUI() — วาด UI Items ทับบน GameObjects
        UiManager.drawUI(g);

        g.setColor(EngineSettings.DEBUG_TEXT_COLOR);
        g.setFont(new Font("", Font.PLAIN, 10));
        g.drawString("Frames:" + Game.getFramesPerSecond(), 0, 20);
        g.drawString("Tick:" + Game.getTickPerSecond(), 0, 40);
        g.dispose();
        bs.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public static void registerStyledObject(StyledObject styObj) {
        Renderer.texturedObject.add(styObj);
    }

    public static void unregisterStyledObject(StyledObject styObj) {
        Renderer.texturedObject.remove(styObj);
    }

}
