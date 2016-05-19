package blocksteroids.ui;

import blocksteroids.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

public class GUI extends JFrame implements Observer, UIConstants {
    
    private InputListener input;
    private BufferedScreen screen;
    private Controller c;

    public GUI() {
        
    }
    
    public void init(Controller c) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Blocksteroids");
        
        screen = new BufferedScreen();
        
        input = new InputListener(c, screen);
        
        this.addKeyListener(input);
        screen.addMouseListener(input);
        screen.addMouseMotionListener(input);
        screen.addMouseWheelListener(input);
        
        this.add(screen, BorderLayout.CENTER);
        //this.setContentPane(screen);
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - WINDOW_WIDTH) / 2;
        int y = (d.height - WINDOW_HEIGHT) / 2;
        
        this.setLocation(x, y);
        
        this.setSize(WINDOW_WIDTH + 2, WINDOW_HEIGHT + 2);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        screen.update(arg);
    }
    
    public void shutDown() {
        this.dispose();
    }
}
