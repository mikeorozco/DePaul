import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javafx.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;

public class drawMap {

	private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        
        JFrame f = new JFrame("Example Layout for Clean Sweep Vacuum");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
        
        // BoxLayout b = new BoxLayout(f.getRootPane(), BoxLayout.Y_AXIS);
        // b.addLayoutComponent(new MyPanel(), constraints);
        // f.add(new MyPanel());
    }

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}

}

class MyPanel extends JPanel {

	private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;
	    
    public MyPanel() {
        //setBorder(BorderFactory.createLineBorder(Color.black));
    	
    	addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });
    }

    private void moveSquare(int x, int y) {
        int OFFSET = - squareW/2;
        if ((squareX != x) || (squareY != y)) {
            squareX = x + OFFSET;
            squareY = y + OFFSET;  
        }
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(250,250);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        
        // Draw Text
        g.drawString("This is my custom Panel!",10,20);
        g.setColor(Color.RED);
        g.fillRect(squareX,squareY,squareW,squareH);
        g.setColor(Color.BLACK);
        g.drawRect(squareX,squareY,squareW,squareH);
        repaint();
    }  
}
