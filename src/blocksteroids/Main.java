package blocksteroids;

import blocksteroids.game.Game;
import blocksteroids.ui.GUI;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        GUI gui = new GUI();
        Controller c = new Controller(game, gui);
        gui.init(c);
        game.setController(c);
        game.addObserver(gui);
        game.run();
    }
}
