package blocksteroids.game.objects;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractExplosion implements ObjectConstants {
    
    private Set<MovingObject> particles;
    private int timer;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public AbstractExplosion(Point2D origin) {
        particles = new HashSet<MovingObject>();
        addParticles(origin);
        moveParticles(5);
        
        timer = 0;
    }
    
    protected abstract void addParticles(Point2D origin);
    
    public final void addParticle(MovingObject mo) {
        particles.add(mo);
    }
    
    public final Set<MovingObject> getParticles() {
        return particles;
    }
    
    public final void moveParticles(long deltatime) {
        
        for (MovingObject mo : particles) {
            mo.move(deltatime);
        }
    }
    
    public final void incrementTimer(long deltatime) {
        timer += deltatime;
    }
    
    public final int getTimer() {
        return timer;
    }
}
