package features;

import java.awt.BorderLayout;
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
	
	private AdjustBar adjustBars[];
	private JLabel labels[];
	private JLabel title;
	private Button apply;
	private JRadioButton hsbSwitch;
	private ImageDisplay imgDisp;
	
	public ControlPanel() {
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
			labels[i].setBackground(Color.RED);
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
	
	public void reset() {
		for (int i = 0; i < adjustBars.length; i++) {
			adjustBars[i].reset();
		}
	}
	
	public void setAdjustable(boolean b) {
		for (int i = 0; i < adjustBars.length; i++) {
			adjustBars[i].setEnabled(b);
		}
		apply.setEnabled(b);
		hsbSwitch.setEnabled(b);
	}
	
	public AdjustBar getAdjustBar(int x) {
		return adjustBars[x];
	}
	
	public AdjustBar[] getAdjustBarArray() {
		return adjustBars;
	}
	
	public void setImageDisplay(ImageDisplay imgDisp) {
		this.imgDisp = imgDisp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (apply.equals(e.getSource())) {
			imgDisp.repaint();
		} else if (hsbSwitch.equals(e.getSource())) {
			if (hsbSwitch.isSelected()) {
				labels[0].setText("   Hue");
				labels[1].setText("   Saturation");
				labels[2].setText("   Brightness");
				imgDisp.setRGBMode(false);
			} else {
				labels[0].setText("   Red");
				labels[1].setText("   Green");
				labels[2].setText("   Blue");
				imgDisp.setRGBMode(true);
			}
			reset();
			imgDisp.repaint();
		}
	}
	
}
