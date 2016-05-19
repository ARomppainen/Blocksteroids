package blocksteroids.game.objects;

import blocksteroids.game.objects.AbstractAsteroid.AsteroidSize;
import java.awt.geom.Point2D;

public interface ObjectFactory {
    public abstract AbstractAsteroid createLargeAsteroid();
    public abstract AbstractAsteroid createMediumAsteroid();
    public abstract AbstractAsteroid createSmallAsteroid();
    
    public abstract AbstractAsteroid createAsteroid(double x, double y, int rotation, AsteroidSize size);
    
    public abstract AbstractBullet createBullet(AbstractShip ship);
    
    public abstract AbstractExplosion createExplosion(Point2D origin);
    
    public abstract AbstractShip createShip();
}
