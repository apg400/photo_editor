package features;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class AdjustBar extends Canvas implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage screen;
	private int xpos; // x position of circle
	private int ypos; // y position of circle
	private boolean isPressed;
	private int height, xcenter;
	
	public AdjustBar() {
		setPreferredSize(new Dimension(220, 40));
		setSize(220, 40);
		xpos = getWidth()/2 - 6;
		ypos = getHeight()/2 - 6;
		height = getHeight()/2;
		xcenter = getWidth()/2 - 6;
		isPressed = false;
		screen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		addMouseListener(this);
		addMouseMotionListener(this);
		update();
	}
	
	//
	public int getXPos() {
		return xpos;
	}
	
	//
	public int getXCenter() {
		return xcenter;
	}
	
	//
	public int getBarWidth() {
		return getWidth()/2 - 6;
	}
	
	//
	public void paint(Graphics g) {
		g.drawImage(screen, 0, 0, this);
	}
	
	//
	public void update() {
		Graphics2D g = (Graphics2D) screen.getGraphics();
		g.setColor(Color.WHITE); 
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawLine(30, height, getWidth()-30, height);
		g.drawLine(30, height - 5, 30, height + 5);
		g.drawLine(getWidth()-30, height - 5, getWidth()-30, height + 5);
		g.drawLine(getWidth()/2, height - 8, getWidth()/2, height - 16);
		//
		g.setColor(Color.WHITE);
		g.fillOval(xpos, ypos, 12, 12);
		g.setColor(Color.LIGHT_GRAY);
		g.drawOval(xpos, ypos, 12, 12);
		repaint();
	}
	
	//
	public void reset() {		
		xpos = xcenter;
		this.update();
	}
	
	public void onClick(MouseEvent e) {
		if (e.getX() > 30 && e.getX() < getWidth()-30 && isPressed) {
			xpos = e.getX() - 6;
			update();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			isPressed = true;
			onClick(e);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		isPressed = false;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		onClick(e);
	}
	
	//
	//
	// Unneeded functions from MouseListener and MouseMotionListener
	@Override
	public void mouseMoved(MouseEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
