package blocksteroids.game.objects;

import blocksteroids.game.util.Vector2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class Asteroid extends AbstractAsteroid {
    
    private static Shape smallShape = null;
    private static Shape mediumShape = null;
    private static Shape largeShape = null;
    
    public Asteroid(AsteroidSize size, float rotation, float rotationSpeed, Vector2D vector) {
        super(size, rotation, rotationSpeed, vector);
    }
    
    @Override
    protected Shape getSmallAsteroid() {
        if (smallShape == null) {
            smallShape = new Rectangle(0, 0, getSize(), getSize());
        }
        
        return smallShape;
    }
    
    @Override
    protected Shape getMediumAsteroid() {
        if (mediumShape == null) {
            mediumShape = new Rectangle(0, 0, getSize(), getSize());
        }
        
        return mediumShape;
    }
    
    @Override
    protected Shape getLargeAsteroid() {
        if (largeShape == null) {
            largeShape = new Rectangle(0, 0, getSize(), getSize());
        }
        
        return largeShape;
    }
}
