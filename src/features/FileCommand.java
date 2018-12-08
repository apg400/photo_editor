package features;

import java.awt.FileDialog;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import main.PhotoFilter;

public class FileCommand {
	
	private PhotoFilter filter;
	
	public FileCommand(PhotoFilter filter) {
		this.filter = filter;
	}
	
	public void openFile() {
		FileDialog loadFD = new FileDialog(filter, "Choose an image...", FileDialog.LOAD);
		loadFD.setVisible(true);
		File file = new File(loadFD.getDirectory() + loadFD.getFile());
		if (file.exists() && (file.toString().contains(".jpg") || file.toString().contains(".png"))) {
			try {
				BufferedImage image = ImageIO.read(file);
				filter.getImageDisplay().setOriginalImage(image);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(filter, "Error reading file.", 
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(filter, "File invalid or not found.", 
					"Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void saveFile() {
		ImageDisplay id = filter.getImageDisplay();
		if (id.isVisible()) {
			FileDialog saveFD = new FileDialog(filter, "Save image...", FileDialog.SAVE);
			saveFD.setVisible(true);
			try {
				BufferedImage product = id.render();
				String filename = saveFD.getFile();
				String ext = "jpg";
				if (filename.contains(".")) {
					ext = filename.substring(filename.indexOf(".") + 1, filename.length());
				} else {
					filename += ".jpg";
				}
				ImageIO.write(product, ext, new File(saveFD.getDirectory() + filename));
			} catch (Exception e) {
				e.toString();
			}
		} else {
			JOptionPane.showMessageDialog(filter, "No file open. Cannot save.");
		}
	}

}
