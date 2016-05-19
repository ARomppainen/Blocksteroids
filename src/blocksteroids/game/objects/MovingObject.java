package blocksteroids.game.objects;

import blocksteroids.game.util.Vector2D;
import blocksteroids.ui.UIConstants;
import java.awt.geom.Point2D;

public abstract class MovingObject implements UIConstants {
    
    private float rotation; // rotation in degrees
    private Vector2D vector;
    
    public MovingObject(float rotation, Vector2D vector) {
        this.rotation = rotation;
        this.vector = vector;
    }
    
    public void move(long deltatime) {
        double speed = vector.length();
        double angle = vector.calcAngle();
        vector.multiply((double)deltatime / 1000);
        Point2D point = vector.endPoint();
        
        if (point.getX() < 0)
            point.setLocation(point.getX() + WINDOW_WIDTH, point.getY());
        else if (point.getX() > WINDOW_WIDTH)
            point.setLocation(point.getX() - WINDOW_WIDTH, point.getY());
        if (point.getY() < 0)
            point.setLocation(point.getX(), point.getY() + WINDOW_HEIGHT);
        else if (point.getY() > WINDOW_HEIGHT)
            point.setLocation(point.getX(), point.getY() - WINDOW_HEIGHT);
        
        
        vector = new Vector2D(point, speed, angle);
    }
    
    public final double getX() {
        return vector.getStart().getX();
    }
    
    public final double getY() {
        return vector.getStart().getY();
    }
    
    public final Point2D getPoint() {
        return vector.getStart();
    }
    
    public final float getRotation() {
        return rotation;
    }
    
    public final void setRotation(float r) {
        rotation = r;
        
        if (rotation > 360) {
            rotation -= 360;
        } else if (rotation < -360) {
            rotation += 360;
        }
    }
    
    public final Vector2D getVector() {
        return vector;
    }
    
    public final void setVector(Vector2D v) {
        vector = v;
    }
}
