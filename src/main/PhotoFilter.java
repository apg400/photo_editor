package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import features.ControlPanel;
import features.FileCommand;
import features.ImageCommand;
import features.ImageDisplay;
import features.Menubar;

public class PhotoFilter extends JFrame  {

	// TODO: allow for drag and drop images into screen (DropTarget)
	
	private static final long serialVersionUID = 1L;
	
	private Panel photoPanel;
	private ControlPanel cpanel;
	private Menubar menubar;
	private ImageDisplay id;
	
	private int height = 700;
	private int width = 1100;
	
	public PhotoFilter() {
		menubar = new Menubar(new FileCommand(this));
		cpanel = new ControlPanel(new ImageCommand(this));
		id = new ImageDisplay(new ImageCommand(this));
		
		setLayout(new BorderLayout());
		setSize(width, height);
		setTitle("Photo Editor");
		setIconImage(new ImageIcon("attributes/photo_icon.png").getImage());
		setJMenuBar(menubar);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		
		add(cpanel, BorderLayout.WEST);
		
		photoPanel = new Panel();
		photoPanel.setVisible(true);
		photoPanel.setBackground(Color.LIGHT_GRAY);
		photoPanel.setPreferredSize(new Dimension(874, 648));
		photoPanel.setSize(new Dimension(874, 648));
		photoPanel.add(id);
		add(photoPanel, BorderLayout.CENTER);
		
	}
	
	public ImageDisplay getImageDisplay() {
		return id;
	}
	
	public ControlPanel getControlPanel() {
		return cpanel;
	}
	
}
