package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageDisplay extends Canvas {
	
	private File file;
	private BufferedImage img;
	private int mode; // 0 - RGB, 1 - HSB
	
	public ImageDisplay() {
		mode = 0;
	}
	
	public Dimension getMinimumSize() {
		return new Dimension(50, 50);
	}
	
	public Dimension getMaximumSize() {
		return new Dimension(1000, 1000);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}
	
	public BufferedImage render() {
		BufferedImage img2 = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g  = img2.getGraphics();
		for (int y = 0; y < img2.getHeight(); y++) {
			for (int x = 0; x < img2.getWidth(); x++) {
				Color tc = new Color(img.getRGB(x, y));
				float hsbVals[] = Color.RGBtoHSB(tc.getRed(), tc.getGreen(), tc.getBlue(), null);
				Color color2;
				if (mode == 0) {
					color2 = new Color(tc.getRed(), tc.getGreen(), tc.getBlue());
				} else {
					color2 = new Color(Color.HSBtoRGB(hsbVals[0], hsbVals[1], hsbVals[2]));
				}
				g.setColor(color2);
				g.fillRect(x, y, 1, 1);
			}
		}
		
		return img2;
	}
	
	public void paint(Graphics g) {
		if (img != null) {
			for (int y = 0; y < getHeight(); y++) {
				for (int x = 0; x < getWidth(); x++) {
					Color tc = new Color(img.getRGB(x * img.getWidth() / getWidth(), y * img.getHeight() / getHeight()));
					float hsbVals[] = Color.RGBtoHSB(tc.getRed(), tc.getGreen(), tc.getBlue(), null);
					Color color2;
					if (mode == 0) {
						color2 = new Color(tc.getRed(), tc.getGreen(), tc.getBlue());
					} else {
						color2 = new Color(Color.HSBtoRGB(hsbVals[0], hsbVals[1], hsbVals[2]));
					}
					g.setColor(color2);
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
			if (img.getWidth() > 880 || img.getHeight() > 680) {
				int wm = img.getWidth() / 880;
				int vm = img.getHeight() / 680;
				lm = wm > vm ? wm : vm;
			}
			this.setSize(new Dimension(img.getWidth() / lm, img.getHeight() / lm));
			this.setVisible(true);
		} catch(Exception e) {
			System.out.println("Fail to open image.");
		}
		//repaint();//0, 0, getWidth(), getHeight());
	}

}
