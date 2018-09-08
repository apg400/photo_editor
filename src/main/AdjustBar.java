package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class AdjustBar extends Canvas implements MouseListener, MouseMotionListener{

	BufferedImage screen;
	private int[] xpxl;
	private int[] ypxl;
	boolean isPressed;
	
	public AdjustBar() {
		xpxl = new int[2];
		ypxl = new int[2];
		xpxl[0] = 0;
		ypxl[0] = 0;
		xpxl[1] = getWidth();
		ypxl[1] = getHeight();
		isPressed = false;
		setPreferredSize(new Dimension(220, 40));
		setSize(220, 40);
		screen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		addMouseListener(this);
		addMouseMotionListener(this);
		refresh();
	}
	
	public void paint(Graphics g) {
		g.drawImage(screen, 0, 0, this);
	}
	
	public void refresh() {
		Graphics2D g = (Graphics2D) screen.getGraphics();
		g.setColor(Color.yellow); //yellow to see size of canvas
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(30, getHeight()/2, getWidth()-30, getHeight()/2);
		g.drawLine(30, getHeight()/2 - 5, 30, getHeight()/2 + 5);
		g.drawLine(getWidth()-30, getHeight()/2 - 5, getWidth()-30, getHeight()/2 + 5);
		g.setColor(Color.GRAY);
		g.fillRect(getWidth()/2 - 5, getHeight()/2 - 10, 10, 20);
		g.setColor(Color.RED);
		g.drawLine(getWidth()/2, getHeight()/2 - 2, getWidth()/2, getHeight()/2 - 10);
		int[] xcoor = {getWidth()/2 - 5, getWidth()/2 + 5,  getWidth()/2};
		int[] ycoor = {getHeight()/2 - 15, getHeight()/2 - 15, getHeight()/2 - 10};
		g.fillPolygon(xcoor, ycoor, 3);
		repaint(0, 0, getWidth(), getHeight());
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		isPressed = false;
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
