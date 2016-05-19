package blocksteroids.game;

import blocksteroids.Config;
import blocksteroids.Controller;
import blocksteroids.game.states.MainMenuState;
import blocksteroids.game.states.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Stack;

public class Game extends Observable {

    private boolean running;
    private Stack<State> states;
    private List<Event> events;
    private long deltatime;
    private Controller c;
    
    public void run() {
        states = new Stack<State>();
        events = new ArrayList<Event>();
        running = true;
        deltatime = 0;
        
        states.push(new MainMenuState(this));

        while (running) {
            
            long time = System.currentTimeMillis();

            State s = states.peek();
            
            synchronized(this) {
                if (!s.handleEvents(events)) {
                    continue;
                }
            }

            if (!s.isInitialized()) {
                s.initialize();
            }
            
            s.updateState(deltatime);

            if (s.hasChanged()) {
                this.setChanged();
                this.notifyObservers(s);
                s.clearChanged();
            }

            deltatime = System.currentTimeMillis() - time;

            if (deltatime < Config.TARGET_FRAME_MS) {
                try {
                    Thread.sleep(Config.TARGET_FRAME_MS - deltatime);
                } catch (Exception e) {}

                deltatime = Config.TARGET_FRAME_MS;
            }

            if (deltatime > Config.MAX_FRAME_MS) {
                deltatime = Config.MAX_FRAME_MS;
            }
        }
    }
    
    public void setController(Controller c) {
        this.c = c;
    }

    public void exit() {
        running = false;
        c.shutDownGUI();
    }
    
    public synchronized void newEvent(Event e) {
        events.add(e);
    }
    
    public void pushState(State s) {
        states.push(s);
    }
    
    public void popState() {
        states.pop();
    }
    
    public void clearStates() {
        states.clear();
    }
}
