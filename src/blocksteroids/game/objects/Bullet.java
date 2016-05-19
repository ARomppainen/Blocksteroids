package blocksteroids.game.objects;

public class Bullet extends AbstractBullet {
    
    public Bullet(AbstractShip ship) {
        super(ship);
    }

    @Override
    public int getWidth() {
        return 3;
    }

    @Override
    public int getHeight() {
        return getWidth();
    }
}
