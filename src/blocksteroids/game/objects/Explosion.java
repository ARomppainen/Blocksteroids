package blocksteroids.game.objects;

import blocksteroids.game.util.Vector2D;
import java.awt.geom.Point2D;
import java.util.Random;

public class Explosion extends AbstractExplosion {

    private static Random rand = new Random();
    
    public Explosion(Point2D origin) {
        super(origin);
    }
    
    @Override
    protected final void addParticles(Point2D origin) {
        int n = PARTICLE_AMOUNT + rand.nextInt(PARTICLE_VARIANCE);
        
        double minAngle = 360.0 / n;
        
        for (int i = 0 ; i < n ; i++) {
            double speed = PARTICLE_SPEED + rand.nextInt(PARTICLE_SPEED_VARIANCE);
            double angle = minAngle * i + rand.nextInt(PARTICLE_ANGLE_VARIANCE);
            
            addParticle(new Particle(new Vector2D(origin, speed, angle)));
        }
    }
    
    private class Particle extends MovingObject {
        
        public Particle(Vector2D v) {
            super(0, v);
        }
    }
}
