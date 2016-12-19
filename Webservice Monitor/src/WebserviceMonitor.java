import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class WebserviceMonitor {
	
	JFrame mainFrame;
	ObjectOutputStream writer;
	ObjectInputStream reader;
	FileOutputStream fileWriter;
	FileInputStream fileReader;
	File file = new File("monitors.ser");
		
	public static void main(String[] args) {
		WebserviceMonitor wsm = new WebserviceMonitor();		
		wsm.buildGUI();
		wsm.loadServices();
		
	}
	
	public void buildGUI() {
		mainFrame = new JFrame("HomeServe Webservice Monitor");
		mainFrame.setSize(800,600);
		mainFrame.setVisible(true);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		mainFrame.getContentPane().add(BorderLayout.CENTER, background);
	
	}
	
	public void loadServices() {
		
		try {
			fileWriter = new FileOutputStream(file);
			fileReader = new FileInputStream(file);
			writer = new ObjectOutputStream(fileWriter);
			reader = new ObjectInputStream(fileReader);
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file " + file);
			e.printStackTrace();
		} catch (IOException io) {
			System.out.println("Unable to initialise IO for " + file);
			io.printStackTrace();
		}
		
	}
	
	public void testService() {
		
	}

}
