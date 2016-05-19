package blocksteroids.game.objects;

import blocksteroids.game.objects.AbstractAsteroid.AsteroidSize;
import blocksteroids.game.util.Vector2D;
import blocksteroids.ui.UIConstants;
import java.awt.geom.Point2D;
import java.util.Random;

public class GameObjectFactory implements ObjectFactory, UIConstants, ObjectConstants {

    private static Random rand = new Random();
    
    private static ObjectFactory instance = null;
    
    private GameObjectFactory() {
        
    }
    
    public static ObjectFactory getInstance() {
        if (instance == null) {
            instance = new GameObjectFactory();
        }
        return instance;
    }

    @Override
    public AbstractAsteroid createLargeAsteroid() {
        int speed = LARGE_ASTEROID_SPEED + rand.nextInt(SPEED_VARIANCE);
        int rotationSpeed = ASTEROID_ROTATION_SPEED + rand.nextInt(ROTATION_SPEED_VARIANCE);
        int rotation = rand.nextInt(360);
        int angle = rand.nextInt(360);
        int x = rand.nextInt(WINDOW_WIDTH);
        int y = rand.nextInt(WINDOW_HEIGHT);

        return new Asteroid(AsteroidSize.LARGE, rotation, rotationSpeed, new Vector2D(new Point2D.Double(x, y), speed, angle));
    }

    @Override
    public AbstractAsteroid createMediumAsteroid() {
        int speed = MEDIUM_ASTEROID_SPEED + rand.nextInt(SPEED_VARIANCE);
        int rotationSpeed = ASTEROID_ROTATION_SPEED + rand.nextInt(ROTATION_SPEED_VARIANCE);
        int rotation = rand.nextInt(360);
        int angle = rand.nextInt(360);
        int x = rand.nextInt(WINDOW_WIDTH);
        int y = rand.nextInt(WINDOW_HEIGHT);

        return new Asteroid(AsteroidSize.MEDIUM, rotation, rotationSpeed, new Vector2D(new Point2D.Double(x, y), speed, angle));
    }

    @Override
    public AbstractAsteroid createSmallAsteroid() {
        int speed = SMALL_ASTEROID_SPEED + rand.nextInt(SPEED_VARIANCE);
        int rotationSpeed = ASTEROID_ROTATION_SPEED + rand.nextInt(ROTATION_SPEED_VARIANCE);
        int rotation = rand.nextInt(360);
        int angle = rand.nextInt(360);
        int x = rand.nextInt(WINDOW_WIDTH);
        int y = rand.nextInt(WINDOW_HEIGHT);

        return new Asteroid(AsteroidSize.SMALL, rotation, rotationSpeed, new Vector2D(new Point2D.Double(x, y), speed, angle));
    }
    
    @Override
    public AbstractAsteroid createAsteroid(double x, double y, int rotation, AsteroidSize size) {
        int speed;
        int rotationSpeed = ASTEROID_ROTATION_SPEED + rand.nextInt(ROTATION_SPEED_VARIANCE);
        int angle = rand.nextInt(360);
        
        if (size == AsteroidSize.SMALL) {
            speed = SMALL_ASTEROID_SPEED + rand.nextInt(SPEED_VARIANCE);
        } else if (size == AsteroidSize.MEDIUM) {
            speed = MEDIUM_ASTEROID_SPEED + rand.nextInt(SPEED_VARIANCE);
        } else {
            speed = LARGE_ASTEROID_SPEED + rand.nextInt(SPEED_VARIANCE);
        }
        
        return new Asteroid(size, rotation, rotationSpeed,  new Vector2D(new Point2D.Double(x, y), speed, angle));
    }

    @Override
    public AbstractBullet createBullet(AbstractShip ship) {
        return new Bullet(ship);
    }

    @Override
    public AbstractExplosion createExplosion(Point2D origin) {
        return new Explosion(origin);
    }

    @Override
    public AbstractShip createShip() {
        return new Ship();
    }

}
