package blocksteroids.game.states;

import blocksteroids.game.Event;
import blocksteroids.game.Game;
import blocksteroids.game.objects.AbstractAsteroid;
import blocksteroids.ui.InputConstants;
import blocksteroids.ui.UIConstants;
import java.util.HashSet;
import java.util.Set;

public class MainMenuState extends State implements UIConstants, InputConstants{

    private Set<AbstractAsteroid> asteroids;
    
    public MainMenuState(Game g) {
        super(g);
    }

    @Override
    public void initialize() {
        asteroids = new HashSet<AbstractAsteroid>();
        
        for (int i = 0 ; i < 2 ; i++) {
            asteroids.add(getFactory().createLargeAsteroid());
        }
        
        for (int i = 0 ; i < 5 ; i++) {
            asteroids.add(getFactory().createMediumAsteroid());
        }
        
        for (int i = 0 ; i < 9 ; i++) {
            asteroids.add(getFactory().createSmallAsteroid());
        }

        this.initialized = true;
        this.setChanged();
    }
    
    @Override
    public void updateState(long deltatime) {
        
        for(AbstractAsteroid a : asteroids) {
            a.rotate(deltatime);
            a.move(deltatime);
        }
        
        this.setChanged();
    }

    @Override
    public boolean handleEvent(Event e) {
        
        if (e.type == TYPE_KEY_DOWN) {
            if (e.code == KEY_N) {
                getGame().pushState(new GameState(getGame()));
            } else if (e.code == KEY_E) {
                getGame().exit();
            }
        }
        
        
        return true;
    }
    
    public Set<AbstractAsteroid> getAsteroids() {
        return asteroids;
    }
}
