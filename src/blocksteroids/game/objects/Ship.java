package blocksteroids.game.objects;

import blocksteroids.game.util.Vector2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

public class Ship extends AbstractShip {
    
    private static Shape shape = null;
    
    public Ship() {
        super(270, new Vector2D(new Point2D.Double(400, 300), 0, 0));
    }
    
    @Override
    public Shape getShape() {
        if (shape == null) {
            Path2D p = new Path2D.Double();
            
            // näitä pisteitä voi lisätä, jos tuntuu että törmäykset menee pieleen
            p.moveTo(21, 0);
            p.lineTo(6, 7.5);
            p.lineTo(-9, 15);
            p.lineTo(-9, 0);
            p.lineTo(-9, -15);
            p.lineTo(6, -7.5);
            p.closePath();
            
            shape = p;
        }
        
        return shape;
    }
    
    @Override
    public Set<Point2D> getEdgePoints() {
        Set<Point2D> points = new HashSet<Point2D>();
        
        AffineTransform at = new AffineTransform();
        
        at.translate(getX(), getY());
        at.rotate(Math.toRadians(getRotation()));
        
        PathIterator pi = getShape().getPathIterator(at);
        double[] coords = new double[6];
        
        while (!pi.isDone()) {
            pi.currentSegment(coords);
            points.add(new Point2D.Double(coords[0], coords[1]));
            pi.next();
        }
        
        return points;
    }
}
