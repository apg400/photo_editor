package main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.Panel;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import features.ControlPanel;

public class ImageEditor extends JFrame {
	
	// TODO: ensure file selected is an image
	// TODO: add menu for opening/saving an image
	// TODO: allow for drag and drop images into screen (DropTarget)
	
	private Panel photoPanel;
	
	private ControlPanel cpanel;
	private TopMenu menu;
	private ImageDisplay id;
	
	public ImageEditor() {
		id = new ImageDisplay();
		menu = new TopMenu(this);
		cpanel = new ControlPanel();
		
		setLayout(new BorderLayout());
		setSize(1100, 700);
		setTitle("Photo Editor");
		setIconImage(new ImageIcon("attributes/photo_icon.png").getImage());
		setJMenuBar(menu);
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
	
	public void openFile() {
		FileDialog loadFD = new FileDialog(this, "Choose an image...", FileDialog.LOAD);
		loadFD.setVisible(true);
		File f = new File(loadFD.getDirectory() + loadFD.getFile());
		if (f.exists()) {
			id.open(f);
			id.setLocation(photoPanel.getWidth()/2 - id.getWidth()/2, photoPanel.getHeight()/2 - id.getHeight()/2);
		} else {
			System.out.println("ERROR: File not found");
		}
	}
	
	public void saveFile() {
		FileDialog saveFD = new FileDialog(this, "Save image...", FileDialog.SAVE);
	}
	
}
