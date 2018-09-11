package features;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import main.ImageEditor;

public class TopMenu extends JMenuBar implements ActionListener {
	
	JMenu fileMenu;
	JMenuItem menuItem;
	ImageEditor editor;
	
	public TopMenu(ImageEditor editor) {
		this.editor = editor;
		fileMenu = new JMenu("File");
		//fileMenu.setMnemonic(KeyEvent.VK_CONTROL);
		add(fileMenu);
		menuItem = new JMenuItem("Open File");
		menuItem.addActionListener(this);
		menuItem.setMnemonic(KeyEvent.VK_F);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		fileMenu.add(menuItem);
		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menuItem.setMnemonic(KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		fileMenu.add(menuItem);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (((AbstractButton) a.getSource()).getText() == "Open File" ) {
			editor.openFile();
			//System.out.printf("menu width: %d\nmenu height: %d", fileMenu.getWidth(), fileMenu.getHeight());
		} else if (((AbstractButton) a.getSource()).getText() == "Save" ) {
			editor.saveFile();
		}
	}
	
}
