package blocksteroids.ui;

import blocksteroids.Controller;
import blocksteroids.game.Event;
import java.awt.event.*;
import javax.swing.JComponent;

public class InputListener implements InputConstants, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{

    private Controller c;
    private JComponent parent;
    
    public InputListener(Controller c, JComponent parent) {
        this.c = c;
        this.parent = parent;
    }
    
    public static int getKey(int keyCode) {
        int key = -1;
        switch (keyCode) {
            case KeyEvent.VK_UP:        key = KEY_UP;    break;
            case KeyEvent.VK_DOWN:      key = KEY_DOWN;    break;
            case KeyEvent.VK_LEFT:      key = KEY_RIGHT;    break;
            case KeyEvent.VK_RIGHT:     key = KEY_LEFT;    break;
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_ENTER:     key = KEY_FIRE; break;
            case KeyEvent.VK_NUMPAD1:   key = KEY_SW;   break;
            case KeyEvent.VK_NUMPAD2:   key = KEY_DOWN;    break;
            case KeyEvent.VK_NUMPAD3:   key = KEY_SE;   break;
            case KeyEvent.VK_NUMPAD4:   key = KEY_RIGHT;    break;
            case KeyEvent.VK_NUMPAD5:   key = KEY_FIRE; break;
            case KeyEvent.VK_NUMPAD6:   key = KEY_LEFT;    break;
            case KeyEvent.VK_NUMPAD7:   key = KEY_NW;   break;
            case KeyEvent.VK_NUMPAD8:   key = KEY_UP;    break;
            case KeyEvent.VK_NUMPAD9:   key = KEY_NE;   break;
            case KeyEvent.VK_ESCAPE:    key = KEY_ESCAPE; break;
            case KeyEvent.VK_N:         key = KEY_N; break;
            case KeyEvent.VK_E:         key = KEY_E; break;
        }
        return key;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        addEvent(TYPE_KEY_DOWN, getKey(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        addEvent(TYPE_KEY_UP, getKey(e.getKeyCode()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        addEvent(TYPE_MOUSE_DOWN, e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        addEvent(TYPE_MOUSE_UP, e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = Math.max(0, Math.min((int) (e.getX() - 1), parent.getWidth() - 1));
        int y = Math.max(0, Math.min((int) (e.getY() - 1), parent.getHeight() - 1));
        addEvent(TYPE_MOUSE_MOVE, x, y, -1);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = Math.max(0, Math.min((int) (e.getX() - 1), parent.getWidth() - 1));
        int y = Math.max(0, Math.min((int) (e.getY() - 1), parent.getHeight() - 1));
        addEvent(TYPE_MOUSE_DRAG, x, y, -1);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        
    }
    
    public void addEvent(int eventType, MouseEvent e) {
        
        int x = Math.max(0, Math.min((int) (e.getX() - 1), parent.getWidth() - 1));
        int y = Math.max(0, Math.min((int) (e.getY() - 1), parent.getHeight() - 1));
        
        int button = -1;
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                button = MOUSE_BUTTON_LEFT;
                break;
            case MouseEvent.BUTTON2:
                button = MOUSE_BUTTON_MIDDLE;
                break;
            case MouseEvent.BUTTON3:
                button = MOUSE_BUTTON_RIGHT;
                break;
        }
        if (button > -1) {
            addEvent(eventType, x, y, button);
        }
    }
    
    public void addEvent(int eventType, int eventCode) {
        addEvent(eventType, -1, -1, eventCode);
    }

    public void addEvent(int eventType, int x, int y, int eventCode) {
        c.newEvent(new Event(eventType, x, y, eventCode));
    }
}
