package blocksteroids.ui;

import blocksteroids.game.objects.*;
import blocksteroids.game.states.GameState;
import blocksteroids.game.states.MainMenuState;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class BufferedScreen extends JPanel implements UIConstants, ObjectConstants {
    
    private static final int I11 = 0;
    private static final int I12 = 1;
    private static final int I13 = 2;
    private static final int I21 = 3;
    private static final int I22 = 4;
    private static final int I23 = 5;
    
    private static final int SIZE = 6;
    
    private BufferedImage img;
    private Graphics2D gfx;
    
    private float[] transform;
    private AffineTransform affine;
    
    public BufferedScreen() {
        img = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
        gfx = (Graphics2D) img.getGraphics();
        
        transform = new float[SIZE];
        affine = new AffineTransform();
        
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    }
    
    public void update(Object o) {
        if (o instanceof MainMenuState) {
            drawMainMenu((MainMenuState) o);
        } else if (o instanceof GameState) {
            drawGame((GameState) o);
        }
        
        this.repaint();
    }

    private void drawMainMenu(MainMenuState mms) {
        gfx.setColor(BACKGROUND_COLOR);
        gfx.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        for (AbstractAsteroid a : mms.getAsteroids()) {
            drawAsteroid(a);
        }

        resetTransform();
        setTransform();
        
        gfx.setFont(TITLE_FONT);
        gfx.setColor(TITLE_COLOR);
        gfx.drawString("BLOCKSTEROIDS", 280, 150);
        
        gfx.setFont(MENU_FONT);
        gfx.drawString("(N)ew game", 280, 250);
        gfx.drawString("(E)xit", 280, 270);
    }
    
    private void drawGame(GameState gs) {
        gfx.setColor(BACKGROUND_COLOR);
        gfx.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        for (AbstractAsteroid a : gs.getAsteroids()) {
            drawAsteroid(a);
        }
        
        drawShip(gs.getShip());
        
        for (AbstractBullet b : gs.getBullets()) {
            drawBullet(b);
        }
        
        for (AbstractExplosion e : gs.getExplosions()) {
            drawExplosion(e);
        }
        
        resetTransform();
        setTransform();
        
        gfx.setColor(TITLE_COLOR);
        
        if (gs.getShip() == null && gs.getExtraShips() == 0) {
            
            gfx.setFont(TITLE_FONT);
            gfx.drawString("Game Over", 320, 200);
            
            gfx.setFont(MENU_FONT);
            gfx.drawString("Final score: " + Long.toString(gs.getScore()), 290, 320);
            gfx.drawString("Press esc to continue", 260, 360);
        } else {
            gfx.setFont(MENU_FONT);
            gfx.drawString("Extra ships: " + Integer.toString(gs.getExtraShips()), LIVES_X, LIVES_Y);
            gfx.drawString("Score: " + Long.toString(gs.getScore()), SCORE_X, SCORE_Y);
        }
        
        //gfx.drawLine(0, 300, 800, 300);
        //gfx.drawLine(400, 0, 400, 600);
    }
    
    public void drawAsteroid(AbstractAsteroid a) {
        resetTransform();
        
        translate((float)a.getX(), (float)a.getY());
        rotate((float)Math.toRadians(a.getRotation()));
        translate(-a.getSize() / 2, -a.getSize() / 2);
        
        setTransform();
        
        gfx.setColor(ASTEROID_COLOR);
        gfx.draw(a.getShape());
    }
    
    public void drawShip(AbstractShip s) {
        
        if (s != null ) {
            resetTransform();
            translate((float) s.getX(), (float) s.getY());
            rotate((float)Math.toRadians(s.getRotation()));

            setTransform();

            gfx.setColor(SHIP_COLOR);
            gfx.draw(s.getShape());
        }
    }
    
    public void drawBullet(AbstractBullet b) {
        resetTransform();
        setTransform();
        
        gfx.setColor(BULLET_COLOR);
        gfx.drawOval((int)b.getX() - 1, (int)b.getY() - 1, b.getWidth(), b.getHeight());
    }
    
    public void drawExplosion(AbstractExplosion e) {
        resetTransform();
        setTransform();
        
        gfx.setColor(EXPLOSION_COLOR);
        
        for(MovingObject mo : e.getParticles()) {
            gfx.drawLine((int)mo.getX(), (int)mo.getY(), (int)mo.getX(), (int)mo.getY());
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }
    
    public void reset() {
        if (gfx != null) {
            gfx.dispose();
            gfx = null;
        }
        
        gfx = (Graphics2D) img.getGraphics();
        resetTransform();
        resetClip();
    }
    
    public void resetClip() {
        gfx.setClip(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
    
    public void setClip(int x, int y, int w, int h) {
        gfx.setClip(x, y, w, h);
    }
    
    public void resetTransform() {
        transform[I11] = 1;
        transform[I12] = 0;
        transform[I13] = 0;
        transform[I21] = 0;
        transform[I22] = 1;
        transform[I23] = 0;
        
        gfx.setTransform(affine);
    }
    
    public void setTransform() {
        affine.setTransform(transform[I11], transform[I21],
                            transform[I12], transform[I22],
                            transform[I13], transform[I23]);
        
        gfx.setTransform(affine);
    }

    public void translate(float x, float y) {
        transform[I13] += x * transform[I11] + y * transform[I12];
        transform[I23] += x * transform[I21] + y * transform[I22];
    }

    public void rotate(float rads) {
        final float cos = (float) Math.cos(rads);
        final float sin = (float) Math.sin(rads);
        final float a11 = cos * transform[I11] + sin * transform[I12];
        final float a12 = -sin * transform[I11] + cos * transform[I12];
        final float a21 = cos * transform[I21] + sin * transform[I22];
        final float a22 = -sin * transform[I21] + cos * transform[I22];
        transform[I11] = a11;
        transform[I12] = a12;
        transform[I21] = a21;
        transform[I22] = a22;
    }

    public void scale(float sx, float sy) {
        transform[I11] *= sx;
        transform[I12] *= sy;
        transform[I21] *= sx;
        transform[I22] *= sy;
    }
}
