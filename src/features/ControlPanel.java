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

public class ControlPanel extends Panel implements ActionListener {
	
	private AdjustBar adjustBars[];
	private JLabel labels[];
	private JLabel title;
	private Button apply;
	private ImageDisplay imgDisp;
	
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
			labels[i].setHorizontalAlignment(JLabel.LEFT);
			labels[i].setVerticalAlignment(JLabel.BOTTOM);
			adjustPanel.add(labels[i]);
			adjustBars[i] = new AdjustBar();
			adjustBars[i].setEnabled(false);
			adjustPanel.add(adjustBars[i]);
		}
		this.add(adjustPanel);
		
		apply = new Button();
		apply.setLabel("Apply");
		apply.addActionListener(this);
		this.add(apply);
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
		}
		
	}
	
	
}
