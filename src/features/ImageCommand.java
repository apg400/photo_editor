package features;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.PhotoFilter;

public class ImageCommand {
	
	private PhotoFilter filter;
	
	public ImageCommand(PhotoFilter filter) {
		this.filter = filter;
	}
	
	public BufferedImage renderImage() {
		BufferedImage ogImage = filter.getImageDisplay().getOriginalImage();
		ControlPanel cpanel = filter.getControlPanel();
		
		BufferedImage newImage = new BufferedImage(ogImage.getWidth(), ogImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g  = newImage.getGraphics();
		Color c1, c2;
		for (int y = 0; y < newImage.getHeight(); y++) {
			for (int x = 0; x < newImage.getWidth(); x++) {
				c1 = new Color(ogImage.getRGB(x, y));
				if (cpanel.isRGB()) {
					int rgb[] = {c1.getRed(), c1.getGreen(), c1.getBlue()};
					c2 = cpanel.evalRGBColor(rgb);
				} else {
					float hsb[] = Color.RGBtoHSB(c1.getRed(), c1.getGreen(), c1.getBlue(), null);
					c2 = cpanel.evalHSBColor(hsb);
				}
				
				g.setColor(c2);
				g.fillRect(x, y, 1, 1);
			}
		}
		
		return newImage;
	}
	
	public void applyChanges() {
		filter.getImageDisplay().update();
	}
	
	public void resetImage() {
		filter.getImageDisplay().reset();
	}
	
	public void resetControlPanel() {
		filter.getControlPanel().reset();
	}

}
