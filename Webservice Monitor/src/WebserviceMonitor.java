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
	JButton newWS = new JButton("Add a Webservice");
			
		
	public static void main(String[] args) {
		WebserviceMonitor wsm = new WebserviceMonitor();		
		wsm.buildGUI();
		wsm.loadWebServices();
		
	}
	
	public void buildGUI() {
		mainFrame = new JFrame("HomeServe Webservice Monitor");
		mainFrame.setSize(800,600);
		mainFrame.setVisible(true);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		mainFrame.getContentPane().add(BorderLayout.CENTER, background);
		
		JButton newWS = new JButton("Add a Webservice");
		newWS.addActionListener(new AddWebService());
		mainFrame.add(BorderLayout.EAST, newWS);
		
		
	}
	
	public void loadWebServices() {
		
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
	
	public class AddWebService implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			JFrame addWSFrame = new JFrame("Add a new Webservice to Monitor");
			addWSFrame.setSize(400,400);
			addWSFrame.setDefaultCloseOperation(0);
			addWSFrame.setVisible(true);
			JTextField wsName = new JTextField();
			JTextArea wsRequestBody = new JTextArea();
			JTextField wsURL = new JTextField();
			
			JPanel addScreen= new JPanel(new BorderLayout());
			addScreen.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			addScreen.add(wsName);
			addScreen.add(wsRequestBody);
			addScreen.add(wsURL);
		}
	}


		
	}
