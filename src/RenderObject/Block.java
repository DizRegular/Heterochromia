package RenderObject;
public class Block extends StaticObject {
    
    public Block(String name, Vector2D pos, Vector2D size, String imageName,String tag) {
        super(name, pos, size, imageName,tag);
    }
    public Block(String name, Vector2D pos, Vector2D size, String imageName) {
        super(name, pos, size, imageName);
    }
    
    
}