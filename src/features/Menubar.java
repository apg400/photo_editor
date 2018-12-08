package features;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Menubar extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 5727961560561827646L;

	JMenu fileMenu;
	JMenuItem openMenuItem, saveMenuItem;
	FileCommand fileCommand;
	
	public Menubar(FileCommand fileCommand) {
		this.fileCommand = fileCommand;
		fileMenu = new JMenu("File");
		this.add(fileMenu);
		openMenuItem = new JMenuItem("Open File");
		openMenuItem.addActionListener(this);
		openMenuItem.setMnemonic(KeyEvent.VK_F);
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		fileMenu.add(openMenuItem);
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(this);
		saveMenuItem.setMnemonic(KeyEvent.VK_S);
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		fileMenu.add(saveMenuItem);
	}

	public void openFile() {
		fileCommand.openFile();
	}
	
	public void saveFile() {
		fileCommand.saveFile();
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		if (openMenuItem.equals(a.getSource())) {
			this.openFile();
		} else if (saveMenuItem.equals(a.getSource())) { 
			this.saveFile();
		}
	}
	
}
