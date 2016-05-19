package blocksteroids.game.states;

import blocksteroids.game.Event;
import blocksteroids.game.Game;
import blocksteroids.game.objects.GameObjectFactory;
import blocksteroids.game.objects.ObjectFactory;
import java.util.List;

public abstract class State {

    protected boolean initialized = false;
    private boolean hasChanged = false;
    
    private ObjectFactory of;
    private Game g;
    
    public State(Game g) {
        of = GameObjectFactory.getInstance();
        this.g = g;
    }
    
    // template method for event handling
    public boolean handleEvents(List<Event> events) {
        boolean stop = false;
        
        for(Event e : events) {
            stop = !handleEvent(e);
            
            if (stop) {
                break;
            }
        }
        
        events.clear();
        return !stop;
    }
    
    public ObjectFactory getFactory() {
        return of;
    }
    
    public Game getGame() {
        return g;
    }
    
    public final boolean hasChanged() {
        return hasChanged;
    }
    
    public final void setChanged() {
        hasChanged = true;
    }
    
    public final void clearChanged() {
        hasChanged = false;
    }
    
    public final boolean isInitialized() {
        return initialized;
    }
    
    public abstract void initialize();
    
    public abstract void updateState(long deltatime);
    
    public abstract boolean handleEvent(Event e);
    
}
