package blocksteroids.game.states;

import blocksteroids.game.Event;
import blocksteroids.game.Game;
import blocksteroids.game.objects.AbstractAsteroid.AsteroidSize;
import blocksteroids.game.objects.*;
import blocksteroids.ui.InputConstants;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

public class GameState extends State implements ObjectConstants, InputConstants {

    public static final int POINTS_PER_ASTEROID = 50;
    public static final int RESPAWN_TIME = 2000;
    public static final int RESPAWN_DISTANCE = 100;
    public static final Point2D RESPAWN_POINT = new Point2D.Double(400, 300);
    
    private AbstractShip ship;
    
    private Set<AbstractAsteroid> asteroids;
    private Set<AbstractBullet> bullets;
    private Set<AbstractExplosion> explosions;
    
    private boolean rotateShipLeft;
    private boolean rotateShipRight;
    private boolean accelerateShip;
    private boolean canFire;
    private boolean shotFired;
    
    private int extraShips;
    private int respawnTimer;
    private int numOfAsteroids;
    
    private long score;
    
    public GameState(Game g) {
        super(g);
    }
    
    @Override
    public void initialize() {
        asteroids = new HashSet<AbstractAsteroid>();
        numOfAsteroids = 2;
        
        ship = getFactory().createShip();
        
        for (int i = 0 ; i < numOfAsteroids ; i++) {
            
            AbstractAsteroid a;
            
            do {
                a = getFactory().createLargeAsteroid();
            } while (distance(ship.getPoint(), a.getPoint()) < RESPAWN_DISTANCE);
            
            asteroids.add(a);
        }
        
        rotateShipLeft = false;
        rotateShipRight = false;
        accelerateShip = false;
        canFire = false;
        shotFired = false;
        
        bullets = new HashSet<AbstractBullet>();
        explosions = new HashSet<AbstractExplosion>();
        
        extraShips = 3;
        respawnTimer = 0;
        score = 0;
        
        this.initialized = true;
        this.setChanged();
    }
    
    @Override
    public void updateState(long deltatime) {
        
        if (ship != null) {
            if (checkShipCollision()) {
                explosions.add(getFactory().createExplosion(ship.getPoint())); // tästä pitää tehdä suurempi!!!
                ship = null;
            }
        }
        
        if (ship != null) {
            ship.move(deltatime);

            if (rotateShipLeft && !rotateShipRight) {
                ship.rotateLeft(deltatime);
            } else if (rotateShipRight && !rotateShipLeft) {
                ship.rotateRight(deltatime);
            }

            if (accelerateShip) {
                ship.accelerate(deltatime);
            }
        } else {
            if (extraShips > 0 && respawnTimer > RESPAWN_TIME) {
                
                boolean respawnShip = true;
                
                for (AbstractAsteroid a : asteroids) {
                    if (distance(a.getPoint(), RESPAWN_POINT) < RESPAWN_DISTANCE) {
                        respawnShip = false;
                    }
                }
                
                if (respawnShip) {
                    ship = getFactory().createShip();
                    extraShips--;
                    respawnTimer = 0;
                }
            } else {
                respawnTimer += deltatime;
            }
        }
        
        for(AbstractAsteroid a : asteroids) {
            a.rotate(deltatime);
            a.move(deltatime);
        }
        
        Set<AbstractBullet> remainingBullets = new HashSet<AbstractBullet>();
        Set<AbstractAsteroid> explodingAsteroids = new HashSet<AbstractAsteroid>();
        
        for (AbstractBullet b: bullets) {
            b.move(deltatime);
            b.incrementTimer(deltatime);
            
            AbstractAsteroid a = checkCollision(b);
            
            if (a != null) {
                explodingAsteroids.add(a);
            }
            
            if (b.getTimer() < BULLET_DURATION && !b.isTargetHit()) {
                remainingBullets.add(b);
            }
        }
        
        for (AbstractAsteroid a : explodingAsteroids) {
            explode(a);
            score += POINTS_PER_ASTEROID;
        }
        
        if (asteroids.isEmpty()) {
            numOfAsteroids++;
            for (int i = 0 ; i < numOfAsteroids ; i++) {
                asteroids.add(getFactory().createLargeAsteroid());
            }
        }
        
        bullets = remainingBullets;
        
        Set<AbstractExplosion> remainingExplosions = new HashSet<AbstractExplosion>();
        
        for (AbstractExplosion e: explosions) {
            e.moveParticles(deltatime);
            e.incrementTimer(deltatime);
            
            if (e.getTimer() < PARTICLE_DURATION) {
                remainingExplosions.add(e);
            }
        }
        
        explosions = remainingExplosions;
        
        if (canFire && !shotFired && ship != null) {
            shotFired = true;
            
            bullets.add(new Bullet(ship));
        }
        
        this.setChanged();
    }

    @Override
    public boolean handleEvent(Event e) {
        
        if (e.type == TYPE_KEY_DOWN) {
            
            if (e.code == KEY_RIGHT) {
                rotateShipLeft = true;
            } else if (e.code == KEY_LEFT) {
                rotateShipRight = true;
            } else if (e.code == KEY_UP) {
                accelerateShip = true;
            } else if (e.code == KEY_FIRE) {
                canFire = true;
            } else if (e.code == KEY_ESCAPE) {
                getGame().popState();
            }
        } else if (e.type == TYPE_KEY_UP) {
            
            if (e.code == KEY_RIGHT) {
                rotateShipLeft = false;
            } else if (e.code == KEY_LEFT) {
                rotateShipRight = false;
            } else if (e.code == KEY_UP) {
                accelerateShip = false;
            } else if (e.code == KEY_FIRE) {
                canFire = false;
                shotFired = false;
            }
        }
        
        return true;
    }
    
    public AbstractShip getShip() {
        return ship;
    }
    
    public Set<AbstractAsteroid> getAsteroids() {
        return asteroids;
    }
    
    public Set<AbstractBullet> getBullets() {
        return bullets;
    }
    
    public Set<AbstractExplosion> getExplosions() {
        return explosions;
    }
    
    public long getScore() {
        return score;
    }
    
    public int getExtraShips() {
        return extraShips;
    }
    
    private double distance(Point2D p1, Point2D p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    private void explode(AbstractAsteroid a) {
        explosions.add(getFactory().createExplosion(a.getPoint()));
        
        if (a.getAsteroidSize() != AsteroidSize.SMALL) {
            
            AsteroidSize size;
            
            if (a.getAsteroidSize() == AsteroidSize.MEDIUM) {
                size = AsteroidSize.SMALL;
            } else {
                size = AsteroidSize.MEDIUM;
            }
            
            for (int i = 0 ; i < 4 ; i++) {
                asteroids.add(getFactory().createAsteroid(a.getX(), a.getY(), (int)a.getRotation(), size));
            }
        }
        
        asteroids.remove(a);
    }
    
    private AbstractAsteroid checkCollision(AbstractBullet b) {
        AbstractAsteroid asteroid = null;
        
        for (AbstractAsteroid a : asteroids) {
            if (a.getArea().contains(b.getPoint())) {
                b.targetHit();
                asteroid = a;
                break;  // bullet can hit only 1 asteroid
            }
        }
        
        return asteroid;
    }
    
    private boolean checkShipCollision() {
        
        boolean shipHit = false;
        
        Set<Point2D> points = ship.getEdgePoints();
        
        for (AbstractAsteroid a : asteroids) {
            for (Point2D p : points) {
                if (a.getArea().contains(p)) {
                    shipHit = true;
                    break;
                }
            }
        }
        
        return shipHit;
    }
}
