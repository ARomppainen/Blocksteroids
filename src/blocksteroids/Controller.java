package blocksteroids;

import blocksteroids.game.Event;
import blocksteroids.game.Game;
import blocksteroids.ui.GUI;

public class Controller {

    private Game game;
    private GUI gui;
    
    public Controller(Game game, GUI gui) {
        this.game = game;
        this.gui = gui;
    }
    
    public void newEvent(Event e) {
        game.newEvent(e);
    }
    
    public void shutDownGUI() {
        gui.shutDown();
    }
}
