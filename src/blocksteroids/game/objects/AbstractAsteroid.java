package blocksteroids.game.objects;

import blocksteroids.game.util.Vector2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public abstract class AbstractAsteroid extends MovingObject implements ObjectConstants {
    
    public enum AsteroidSize {
        LARGE(80),
        MEDIUM(40),
        SMALL(20);
        
        private int size;
        
        private AsteroidSize(int size) {
            this.size = size;
        }
        
        public int getSize() {
            return size;
        }
    }
    
    private Asteroid.AsteroidSize size;
    private float rotationSpeed; // rotation speed in degrees per second
    
    public AbstractAsteroid(Asteroid.AsteroidSize size, float rotation, float rotationSpeed, Vector2D vector) {
        super(rotation, vector);
        this.size = size;
        this.rotationSpeed = rotationSpeed;
    }
    
    public final Shape getShape() {
        
        Shape s;
        
        switch (size) {
            case SMALL:  s = getSmallAsteroid(); break;
            case MEDIUM: s = getMediumAsteroid(); break;
            case LARGE:  s = getLargeAsteroid(); break;
            default: s = null;
        }
        
        return s;
    }
    
    protected abstract Shape getSmallAsteroid();
    
    protected abstract Shape getMediumAsteroid();
    
    protected abstract Shape getLargeAsteroid();
    
    // dodiin, nyt tää toimii, pitää vielä optimoida et tää area tehdään vaan kerran per tarkistussykli
    public final Area getArea() {
        Area a = new Area(this.getShape());
        
        a.transform(AffineTransform.getTranslateInstance(-size.getSize() / 2, -size.getSize() / 2));
        a.transform(AffineTransform.getRotateInstance(Math.toRadians(getRotation())));
        a.transform(AffineTransform.getTranslateInstance(getX(), getY()));
        
        return a;
    }

    public final float getRotationSpeed() {
        return rotationSpeed;
    }
    
    public final int getSize() {
        return size.getSize();
    }
    
    public final Asteroid.AsteroidSize getAsteroidSize() {
        return size;
    }
    
    public void rotate(long deltatime) {
        setRotation(getRotation() + rotationSpeed * deltatime / 1000);
    }
}
