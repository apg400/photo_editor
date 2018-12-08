package features;

import java.awt.Button;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class ControlPanel extends Panel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private AdjustBar adjustBars[];
	private JLabel labels[];
	private JLabel title;
	private Button apply;
	private JRadioButton hsbSwitch;
	
	private boolean rgb;
	private ImageCommand imageCommand;
	
	//
	public ControlPanel(ImageCommand imageCommand) {
		this.imageCommand = imageCommand;
		rgb = true;
		setLayout(new FlowLayout());
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setVisible(true);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(220, 648));
		setSize(new Dimension(220, 648));
		
		title = new JLabel(" COLOR ADJUSTMENT");
		title.setPreferredSize(new Dimension(220, 30));
		title.setSize(new Dimension(220, 30));
		title.setForeground(Color.BLACK);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setHorizontalAlignment(JLabel.CENTER);
		this.add(title);
		
		Panel adjustPanel = new Panel();
		adjustPanel.setLayout(new GridLayout(12,1));
		adjustPanel.setBackground(Color.WHITE);
		String text[] = {"   Red", "   Green", "   Blue"};
		labels = new JLabel[3];
		adjustBars = new AdjustBar[3];
		for (int i = 0; i < adjustBars.length; i++) {
			labels[i] = new JLabel(text[i]);
			labels[i].setHorizontalAlignment(JLabel.LEFT);
			labels[i].setVerticalAlignment(JLabel.BOTTOM);
			adjustPanel.add(labels[i]);
			adjustBars[i] = new AdjustBar();
			adjustBars[i].setEnabled(false);
			adjustPanel.add(adjustBars[i]);
		}
		this.add(adjustPanel);
		
		Panel buttonPanel = new Panel();
		buttonPanel.setLayout(new GridLayout(1, 2));
		apply = new Button();
		apply.setLabel("    Apply    ");
		apply.addActionListener(this);
		apply.setEnabled(false);
		hsbSwitch = new JRadioButton();
		hsbSwitch.setText("HSB");
		hsbSwitch.addActionListener(this);
		hsbSwitch.setEnabled(false);
		buttonPanel.add(hsbSwitch);
		buttonPanel.add(apply);
		
		this.add(buttonPanel);
	}
	
	//
	public boolean isRGB() {
		return rgb;
	}
	
	//
	public void reset() {
		for (int i = 0; i < adjustBars.length; i++) {
			adjustBars[i].reset();
		}
		this.setAdjustable(true);
	}
	
	//
	public void setAdjustable(boolean b) {
		for (int i = 0; i < adjustBars.length; i++) {
			adjustBars[i].setEnabled(b);
		}
		apply.setEnabled(b);
		hsbSwitch.setEnabled(b);
	}
	
	// Calculates the RGB values based on the position of the bar.
	public Color evalRGBColor(int[] og_rgb) {
		int rgb[] = new int[3];
		int xcenter = adjustBars[0].getXCenter();
	
		for (int i = 0; i < 3; i++) {
		    int calc = (adjustBars[i].getXPos() * og_rgb[i]) / xcenter; 
		    rgb[i] = (calc >= 255) ? 255 : calc;
		}
		
		return new Color(rgb[0], rgb[1], rgb[2]);
	}	
		
	//
	public Color evalHSBColor(float[] og_hsb) {
		float hsb[] = new float[3];
		int width = adjustBars[0].getBarWidth(); //getWidth()/2 - 6;
		
		for (int i = 0; i < 3; i++) {
		    float calc = (((float) adjustBars[i].getXPos()) / ((float) width) * og_hsb[i]);
		    hsb[i] = calc >= 1.0f ? 1.0f : calc;
		}
		return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (apply.equals(e.getSource())) {
			imageCommand.applyChanges();
			
		} else if (hsbSwitch.equals(e.getSource())) {
			if (hsbSwitch.isSelected()) {
				rgb = false;
				labels[0].setText("   Hue");
				labels[1].setText("   Saturation");
				labels[2].setText("   Brightness");
			} else {
				rgb = true;
				labels[0].setText("   Red");
				labels[1].setText("   Green");
				labels[2].setText("   Blue");
			}
			reset();
			imageCommand.resetImage();
		}
	}
	
}
