package main;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TopMenu extends JMenuBar implements ActionListener {
	
	JMenu file;
	JMenuItem menuItem;
	ImageEditor editor;
	
	public TopMenu(ImageEditor editor) {
		this.editor = editor;
		file = new JMenu("File");
		add(file);
		menuItem = new JMenuItem("Open file");
		menuItem.addActionListener(this);
		file.add(menuItem);
		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		file.add(menuItem);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (((AbstractButton) a.getSource()).getText() == "Open file" ) {
			System.out.println("Open file");
			editor.openFile();
		} else if (((AbstractButton) a.getSource()).getText() == "Save" ) {
			System.out.println("Save");
			editor.saveFile();
		}
		
	}
	
}
