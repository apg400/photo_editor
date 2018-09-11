package features;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JLabel;

public class ControlPanel extends Panel {
	
	private AdjustBar adjustBars[];
	private JLabel labels[];
	private JLabel title;
	
	public ControlPanel() {
		setLayout(new FlowLayout());
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setVisible(true);
		setBackground(Color.white);
		setPreferredSize(new Dimension(220, 648));
		setSize(new Dimension(220, 648));
		
		title = new JLabel(" COLOR ADJUSTMENT ----------------");
		title.setPreferredSize(new Dimension(220, 30));
		title.setSize(new Dimension(220, 30));
		title.setForeground(Color.BLACK);
		title.setVerticalAlignment(JLabel.BOTTOM);
		this.add(title);
		
		Panel adjustPanel = new Panel();
		adjustPanel.setLayout(new GridLayout(12,1));
		adjustPanel.setBackground(Color.WHITE);
		String text[] = {"   Red", "   Green", "   Blue", "   Hue", "   Saturation", "   Brightness"};
		labels = new JLabel[6];
		adjustBars = new AdjustBar[6];
		for (int i = 0; i < adjustBars.length; i++) {
			labels[i] = new JLabel(text[i]);
			labels[i].setBackground(Color.RED);
			//labels[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			labels[i].setHorizontalAlignment(JLabel.LEFT);
			labels[i].setVerticalAlignment(JLabel.BOTTOM);
			adjustPanel.add(labels[i]);
			adjustBars[i] = new AdjustBar();
			adjustPanel.add(adjustBars[i]);
		}
		this.add(adjustPanel);
	}
	
	
}
