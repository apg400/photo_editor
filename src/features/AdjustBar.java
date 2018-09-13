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

public class AdjustBar extends Canvas implements MouseListener, MouseMotionListener{

	private BufferedImage screen;
	private int xpxl;
	private int ypxl;
	private boolean isPressed;
	private int height;
	
	public AdjustBar() {
		setPreferredSize(new Dimension(220, 40));
		setSize(220, 40);
		xpxl = getWidth()/2 - 6;
		ypxl = getHeight()/2 - 6;
		height = getHeight()/2;
		isPressed = false;
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
		g.setColor(Color.white ); //yellow to see size of canvas
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(30, height, getWidth()-30, height);
		g.drawLine(30, height - 5, 30, height + 5);
		g.drawLine(getWidth()-30, height - 5, getWidth()-30, height + 5);
		g.setColor(Color.BLACK);
		g.drawLine(getWidth()/2, height - 8, getWidth()/2, height - 16);
		g.setColor(Color.GRAY);
		g.fillOval(xpxl, ypxl, 12, 12);
		repaint();
	}
	
	public void reset() {
		Graphics2D g = (Graphics2D) screen.getGraphics();
		g.setColor(Color.white ); //yellow to see size of canvas
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(30, height, getWidth()-30, height);
		g.drawLine(30, height - 5, 30, height + 5);
		g.drawLine(getWidth()-30, height - 5, getWidth()-30, height + 5);
		g.setColor(Color.BLACK);
		g.drawLine(getWidth()/2, height - 8, getWidth()/2, height - 16);
		g.setColor(Color.GRAY);
		g.fillOval(getWidth()/2 - 6, height - 6, 12, 12);
		repaint();
	}
	
	public int evalRGBColor(int input) {
		int width = getWidth()/2 - 6;
		int calc = (int) (((float) xpxl) / ((float) width) * input);
		int output = calc >= 255 ? 255 : calc;
		return output;
	}
	
	public float evalHSBColor(float input) {
		int width = getWidth()/2 - 6;
		float calc = (((float) xpxl) / ((float) width) * input);
		float output = calc >= 1.0f ? 1.0f : calc;
		return output;
	}
	
	public void onClick(MouseEvent e) {
		if (e.getX() > 30 && e.getX() < getWidth()-30 && isPressed) {
			xpxl = e.getX() - 6;
			refresh();
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
	
	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
