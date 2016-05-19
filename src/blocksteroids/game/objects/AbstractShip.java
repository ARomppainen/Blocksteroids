package blocksteroids.game.objects;

import blocksteroids.game.util.Vector2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.Set;

public abstract class AbstractShip extends MovingObject implements ObjectConstants {
    
    public AbstractShip(float rotation, Vector2D vector) {
        super(rotation, vector);
    }
    
    public abstract Shape getShape();
    public abstract Set<Point2D> getEdgePoints();
    
    public final void rotateLeft(long deltatime) {
        setRotation(getRotation() - SHIP_ROTATION_SPEED * deltatime / 1000.0f);
    }
    
    public final void rotateRight(long deltatime) {
        setRotation(getRotation() + SHIP_ROTATION_SPEED * deltatime / 1000.0f);
    }
    
    public final void accelerate(long deltatime) {
        
        Vector2D v = new Vector2D(null, SHIP_ACCELERATION * deltatime / 1000.0, getRotation());
        getVector().sum(v);
        
        if (getVector().length() > MAX_SHIP_SPEED) {
            setVector(new Vector2D(getVector().getStart(), MAX_SHIP_SPEED, getVector().calcAngle()));
        }
    }
}
