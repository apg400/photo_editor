package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TopMenu extends JMenuBar implements ActionListener {
	
	JMenu file;
	JMenuItem menuItem;
	
	public TopMenu() {
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
		if (((JMenuItem) a.getSource()).getText() == "Open file" ) {
			System.out.println("Open file");
		} else if (((JMenuItem) a.getSource()).getText() == "Save" ) {
			System.out.println("Save");
		}
		
	}
	
}
