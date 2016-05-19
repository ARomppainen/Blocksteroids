package blocksteroids.game.objects;

import java.awt.Color;

public interface ObjectConstants {
    
    // asteroids
    public static final int LARGE_ASTEROID_SPEED = 30;
    public static final int MEDIUM_ASTEROID_SPEED = 50;
    public static final int SMALL_ASTEROID_SPEED = 70;
    public static final int SPEED_VARIANCE = 40;
    
    public static final int ASTEROID_ROTATION_SPEED = -150;
    public static final int ROTATION_SPEED_VARIANCE = 300;
    
    public static final Color ASTEROID_COLOR = Color.BLUE;
    
    // ship
    public static final int SHIP_ACCELERATION = 400;        // pixels per second^2
    public static final int MAX_SHIP_SPEED = 300;           // pixels per second
    public static final float SHIP_ROTATION_SPEED = 360;    // degrees per second
    
    public static final Color SHIP_COLOR = Color.ORANGE;
    
    // bullet
    public static final int BULLET_SPEED = 700;
    public static final int BULLET_DURATION = 400;         // milliseconds
    
    public static final Color BULLET_COLOR = Color.WHITE;
    
    // explosion
    
    public static final int PARTICLE_AMOUNT = 7;
    public static final int PARTICLE_VARIANCE = 5;
    
    public static final int PARTICLE_SPEED = 200;
    public static final int PARTICLE_SPEED_VARIANCE = 300;
    
    public static final int PARTICLE_ANGLE_VARIANCE = 60;
    
    public static final int PARTICLE_DURATION = 500;
    
    public static final Color EXPLOSION_COLOR = Color.YELLOW;
}
