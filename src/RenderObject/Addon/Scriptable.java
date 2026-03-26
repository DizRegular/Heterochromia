package RenderObject.Addon;
public interface Scriptable {
    abstract public void onCreate();
    abstract public void process(double deltaTime);
    abstract public void onDestroy();
}
