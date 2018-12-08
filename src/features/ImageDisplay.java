package features;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageDisplay extends Canvas {

	private static final long serialVersionUID = 1L;

	private BufferedImage originalImage;
	private BufferedImage image;
	private ImageCommand imageCommand;
	
	
	public ImageDisplay(ImageCommand imageCommand) {
		this.imageCommand = imageCommand;
		setVisible(false);
	}
	
	public Dimension getMaximumSize() {
		return new Dimension(874, 648);
	}
	
	public BufferedImage render() {
		return imageCommand.renderImage();
	}
	
	public void update() {
		image = this.render();
		repaint();
	}
	
	public void reset() {
		image = originalImage;
		repaint();
	}
	
	public void paint(Graphics g) {
		//long startTime = System.currentTimeMillis();

		if (image != null) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
			//centerDisplay();
		}
		
		//long endTime = System.currentTimeMillis();
		//System.out.println("That took " + (endTime - startTime) + " milliseconds"); // 1580 ms
	}
	
	public void setOriginalImage(BufferedImage img) {
		originalImage = img;
		image = originalImage; 
		int lm = 1;
		if (img.getWidth() > 870 || img.getHeight() > 640) {
			lm = getLargestDivisor(img);
		}
		this.setPreferredSize(new Dimension(img.getWidth() / lm, img.getHeight() / lm));
		this.setSize(new Dimension(img.getWidth() / lm, img.getHeight() / lm));
		this.setVisible(true);
		imageCommand.resetControlPanel();
		centerDisplay();
	}
	
	public BufferedImage getOriginalImage() {
		return originalImage;
	}
	
	
	/* Checks whether the width of the image relative to the panel is greater 
	than the height relative to the height.
	This value is used to divide both the width and height of the image to ensure it fits in the panel. */
	public int getLargestDivisor(BufferedImage img) {
		int widthDiv = img.getWidth() / 870 + 1;
		int heightDiv = img.getHeight() / 640 + 1;
		return (widthDiv > heightDiv) ? widthDiv : heightDiv;
	}
	
	public void centerDisplay() {
		this.setLocation(getParent().getWidth()/2 - this.getWidth()/2, getParent().getHeight()/2 - this.getHeight()/2);
	}

}
