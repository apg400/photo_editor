package features;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImageDisplay extends Canvas {
	
	private File file;
	private BufferedImage img;
	private ControlPanel cpanel;
	private int[] rgb;
	private int[] hsb;
	private boolean rgbMode;
	
	
	public ImageDisplay(ControlPanel cpanel) {
		this.cpanel = cpanel;
		setVisible(false);
		
		rgbMode = true;
		
		rgb = new int[3];
		hsb = new int[3];
		
		for (int i = 0; i < rgb.length; i++) {
			rgb[i] = 0;
			hsb[i] = 0;
		}
	}
	
	public Dimension getMaximumSize() {
		return new Dimension(874, 648);
	}
	
	public BufferedImage render() {
		BufferedImage img2 = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g  = img2.getGraphics();
		Color c1, c2;
		for (int y = 0; y < img2.getHeight(); y++) {
			for (int x = 0; x < img2.getWidth(); x++) {
				c1 = new Color(img.getRGB(x, y));
				
				if (rgbMode) {
					rgb[0] = cpanel.getAdjustBar(0).evalRGBColor(c1.getRed());
					rgb[1] = cpanel.getAdjustBar(1).evalRGBColor(c1.getGreen());
					rgb[2] = cpanel.getAdjustBar(2).evalRGBColor(c1.getBlue());
					c2 = new Color(rgb[0], rgb[1], rgb[2]);
				} else {
					float hsb[] = Color.RGBtoHSB(c1.getRed(), c1.getGreen(), c1.getBlue(), null);
					hsb[0] = cpanel.getAdjustBar(0).evalHSBColor(hsb[0]);
					hsb[1] = cpanel.getAdjustBar(1).evalHSBColor(hsb[1]);
					hsb[2] = cpanel.getAdjustBar(2).evalHSBColor(hsb[2]);
					c2 = new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
				}
				
				g.setColor(c2);
				g.fillRect(x, y, 1, 1);
			}
		}
		
		return img2;
	}
	
	public void paint(Graphics g) {
		if (img != null) {
			Color c1, c2;
			for (int y = 0; y < getHeight(); y++) {
				for (int x = 0; x < getWidth(); x++) {
					c1 = new Color(img.getRGB(x * img.getWidth() / getWidth(), y * img.getHeight() / getHeight()));
					
					if (rgbMode) {
						rgb[0] = cpanel.getAdjustBar(0).evalRGBColor(c1.getRed());
						rgb[1] = cpanel.getAdjustBar(1).evalRGBColor(c1.getGreen());
						rgb[2] = cpanel.getAdjustBar(2).evalRGBColor(c1.getBlue());
						c2 = new Color(rgb[0], rgb[1], rgb[2]);
					} else {
						float hsb[] = Color.RGBtoHSB(c1.getRed(), c1.getGreen(), c1.getBlue(), null);
						hsb[0] = cpanel.getAdjustBar(0).evalHSBColor(hsb[0]);
						hsb[1] = cpanel.getAdjustBar(1).evalHSBColor(hsb[1]);
						hsb[2] = cpanel.getAdjustBar(2).evalHSBColor(hsb[2]);
						c2 = new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
					}
					
					g.setColor(c2);
					g.fillRect(x, y, 1, 1);
				}
			}
		}
	}
	
	public void open(File file) {
		this.file = file;
		try {
			img = ImageIO.read(this.file);
			// divide the larger value by the panel value and use that to find the value that the picture must be reduced by
			int lm = 1;
			if (img.getWidth() > 870 || img.getHeight() > 640) {
				int wm = img.getWidth() / 870 + 1;
				int vm = img.getHeight() / 640 + 1;
				lm = wm > vm ? wm : vm;
			}
			resetControlPanel();
			this.setPreferredSize(new Dimension(img.getWidth() / lm, img.getHeight() / lm));
			this.setSize(new Dimension(img.getWidth() / lm, img.getHeight() / lm));
			this.setVisible(true);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Error reading file.", 
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void resetControlPanel() {
		cpanel.reset();
		cpanel.setAdjustable(true);
	}
	
	public void setRGBMode(boolean b) {
		rgbMode = b;
	}

}
