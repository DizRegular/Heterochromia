package UniverseEngine;

import RenderObject.*;
import java.util.ArrayList;
import java.util.concurrent.*;

public class GameEventListener {
    private static final ExecutorService eventPool = Executors.newCachedThreadPool();
    
    public static void handleTouch(GameObject eventSource, GameObject eventCause,String msg) {
        if (eventSource instanceof touchable t) {
            eventPool.execute(() -> {
                t.onTouched(eventCause);
            });
        }
    }
    
    public static void handleDelete(BaseObject eventSource) {
        eventPool.execute(() -> {
                eventSource.onDestroy();
            });
    }
    
    public static void handleCreate(BaseObject eventSource) {
        eventPool.execute(() -> {
                eventSource.onCreate();
            });
    }
    
    public static void handleInput(ArrayList<InputListener> inputListenersObject) {
        eventPool.execute(() -> {
            for (InputListener listener : inputListenersObject) {
                listener.onInput();
            }
            });
    }
    
}
