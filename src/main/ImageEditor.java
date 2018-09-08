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

public class ImageEditor {
	
	// TODO: ensure file selected is an image
	// TODO: add menu for opening/saving an image
	// TODO: allow for drag and drop images into screen (DropTarget)
	
	private JFrame view;
	private Button save;
	private FileDialog loadFD;
	private FileDialog saveFD;
	private ImageDisplay id;
	private AdjustBar topBar;
	private Panel controlPanel;
	private Panel photoPanel;
	private TopMenu menu;
	
	public ImageEditor() {
		view = new JFrame();
		save = new Button("SAVE");
		//saveFD = new FileDialog(view, "Save image...", FileDialog.SAVE);
		id = new ImageDisplay();
		topBar = new AdjustBar();
		menu = new TopMenu();
		
		view.setLayout(new BorderLayout());
		//view.setPreferredSize(new Dimension(1200, 800));
		view.setSize(1100, 700);
		view.setTitle("Photo Editor");
		view.setIconImage(new ImageIcon("attributes/photo_icon.png").getImage());
		view.setJMenuBar(menu);
		view.setResizable(false);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setVisible(true);
		view.setLocationRelativeTo(null);
		
		controlPanel = new Panel();
		controlPanel.setLayout(new GridLayout(1, 3));
		controlPanel.add(topBar);
		controlPanel.setVisible(true);
		controlPanel.setBackground(Color.white);
		controlPanel.setPreferredSize(new Dimension(220, 680));
		//controlPanel.setSize(new Dimension(50, 680));
		view.add(controlPanel, BorderLayout.WEST);
		
		photoPanel = new Panel();
		//photoPanel.setLayout(new BorderLayout());
		photoPanel.setVisible(true);
		photoPanel.setBackground(Color.LIGHT_GRAY);
		photoPanel.setPreferredSize(new Dimension(880, 680));
		photoPanel.add(id);
		view.add(photoPanel, BorderLayout.CENTER);
		
		loadFD = new FileDialog(view, "Choose an image...", FileDialog.LOAD);
		loadFD.setVisible(true);
		File f = new File(loadFD.getDirectory() + loadFD.getFile());
		if (f.exists()) {
			id.open(f);
			id.setLocation(photoPanel.getWidth()/2 - id.getWidth()/2, photoPanel.getHeight()/2 - id.getHeight()/2);
			System.out.println("Picture found!");
		} else {
			System.out.println("ERROR: File not found.");
		}
	}
	
}
