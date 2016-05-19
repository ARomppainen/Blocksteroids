package blocksteroids.game.util;

import java.awt.geom.Point2D;
//import java.text.DecimalFormat;

public class Vector2D {

    private Point2D start;
    
    private double i;
    private double j;
    
    //private static DecimalFormat df = new DecimalFormat();

    public Vector2D(double i, double j, Point2D start) {
        this.i = i;
        this.j = j;
        this.start = start;
    }

    public Vector2D(Point2D start, double length, double angle) { // angle in degrees

        if (angle == 0) {
            i = length;
            j = 0;
        } else if (angle == 180) {
            i = length * -1;
            j = 0;
        } else if (angle == 90) {
            j = length;
            i = 0;
        } else if (angle == 270) {
            j = length * -1;
            i = 0;
        } else {
            i = Math.cos(Math.toRadians(angle)) * length;
            j = Math.sin(Math.toRadians(angle)) * length;
        }

        this.start = start;
    }

    public Vector2D(Point2D begin, Point2D end) {
        i = end.getX() - begin.getX();
        j = end.getY() - begin.getY();
        this.start = begin;
    }
    
    public static Vector2D sum(Vector2D v1, Vector2D v2) {
        double i = v1.getI() + v2.getI();
        double j = v1.getJ() + v2.getJ();
        Point2D start = v1.getStart();

        return new Vector2D(i, j, start);
    }
    
    public void sum(Vector2D v) {
        this.i += v.getI();
        this.j += v.getJ();
    }

    public static Vector2D negation(Vector2D v) {
        double i = v.getI() * -1;
        double j = v.getJ() * -1;
        Point2D start = v.getStart();

        return new Vector2D(i, j, start);
    }

    public static double scalarProduct(Vector2D v1, Vector2D v2) {
        return v1.getI() * v2.getI() + v1.getJ() * v2.getJ();
    }
    
    public void multiply(double d) {
        i = i * d;
        j = j * d;
    }

    public double length() {
        return Math.sqrt((i * i) + (j * j));
    }

    public Point2D endPoint() {
        return new Point2D.Double(start.getX() + i, start.getY() + j);
    }

    public double calcAngle() { // ei nollavektoreita!
        
        double angle;
        Vector2D v = new Vector2D(1, 0, new Point2D.Double(0, 0));

        if (scalarProduct(this, v) == 0) {
            if (this.getJ() > 0) {
                angle = 90;
            } else {
                angle = 270;
            }
        } else {
            angle = Math.toDegrees(Math.acos(scalarProduct(this, v) / this.length()));

            if (this.getJ() < 0) {
                angle = 360 - angle;
            }
        }

        return angle;
    }

    public Point2D getStart() {
        return start;
    }

    private double getI() {
        return i;
    }

    private double getJ() {
        return j;
    }
    
    public void print() {
        System.out.println(this);
    }
    
    @Override
    public String toString() {
        return this.getI() + "i + " + this.getJ() + "j";
    }
}
