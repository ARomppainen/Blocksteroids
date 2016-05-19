package blocksteroids.game.objects;

import blocksteroids.game.util.Vector2D;

public abstract class AbstractBullet extends MovingObject implements ObjectConstants {
    
    private int timer;
    private boolean targetHit;
    
    public AbstractBullet(AbstractShip ship) {
        super(0, new Vector2D(ship.getPoint(), BULLET_SPEED, ship.getRotation()));
        
        timer = 0;
        targetHit = false;
        
        move(40);
    }
    
    public abstract int getWidth();
    public abstract int getHeight();
    
    public final void incrementTimer(long deltatime) {
        timer += deltatime;
    }
    
    public final int getTimer() {
        return timer;
    }
    
    public final void targetHit() {
        targetHit = true;
    }
    
    public final boolean isTargetHit() {
        return targetHit;
    }
}
