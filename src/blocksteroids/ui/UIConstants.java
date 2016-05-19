package blocksteroids.ui;

import java.awt.Color;
import java.awt.Font;

public interface UIConstants {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    
    public static final Font TITLE_FONT = new Font("Courier New", Font.BOLD, 30);
    public static final Font MENU_FONT = new Font("Courier New", Font.PLAIN, 24);
            
    public static final Color TITLE_COLOR = Color.WHITE;
    
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    
    public static final int SCORE_X = 550;
    public static final int SCORE_Y = 25;
    
    public static final int LIVES_X = 25;
    public static final int LIVES_Y = SCORE_Y;
}
