package com.ecotravel.main;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserInput extends JFrame implements ActionListener
{
	private static String vehicleType;
	private static String sortBy;
	private static GridBagConstraints gbc;
	private static JPanel panel;
	private static JButton factsButton;
	private static JButton inputButton;
	private static JLabel sortByLabel;
	private static JRadioButton emissionsRadioButton;
	private static JRadioButton timeRadioButton;
	private static JLabel label;
	private static JTextArea routesArea;
	private static JLabel map;
	//private static Image image;
	private static final String IMAGE_PATH = "https://previews.123rf.com/images/jannoon028/jannoon0281410/jannoon028141000642/33085360-green-eco-earth-green-earth-with-trees-vector-illustration.jpg";
	private static JLabel funfacts;
	private static ArrayList<Route> routesList; 
    	private static Sort sort;
	private static final String[] colors={"0x0bba1f", "0x7c0a21", "0xe13d95", "0x813498"};
	
	public UserInput() 
	{
        	setTitle("EcoTravel");
        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        	setResizable(false);
        	setSize(2000, 4000);
		factsButton = new JButton("Fun Facts");
		inputButton = new JButton("Input Route Info");
		
		//Sort option radio buttons
		sortByLabel = new JLabel("Sort routes by:");
		emissionsRadioButton = new JRadioButton("Carbon Emissions");
		emissionsRadioButton.setSelected(true);
		sortBy = "Emission";
		emissionsRadioButton.setOpaque(false);
		timeRadioButton = new JRadioButton("Time");
		timeRadioButton.setOpaque(false);
		ButtonGroup sortButtons = new ButtonGroup();
		sortButtons.add(emissionsRadioButton);
		sortButtons.add(timeRadioButton);
		
		factsButton.addActionListener(this);
		inputButton.addActionListener(this);
		emissionsRadioButton.addActionListener(this);
		timeRadioButton.addActionListener(this);
	}
	 
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		String action = ae.getActionCommand();
        	if (action.equals("Input Route Info")) 
		{	
            		String startLocation = inputStartLocation();
			while(startLocation == "" || (!startLocation.matches(".*[a-zA-Z]+.*")) || !(startLocation.matches(".*\\d.*")))
			{
				JOptionPane.showMessageDialog(null, "Please enter a valid address with numbers and letters.");
				startLocation = inputStartLocation();
			}
			
           		String destLocation = inputDestLocation();
			while(destLocation == "" || (!destLocation.matches(".*[a-zA-Z]+.*")) || !(destLocation.matches(".*\\d.*")))
			{
				JOptionPane.showMessageDialog(null, "Please enter a valid address with numbers and letters.");
				destLocation = inputDestLocation();
			}
			int maxTime = 0;
			try {
				maxTime = inputMaxTime();
				while(maxTime < 1)
				{	
					JOptionPane.showMessageDialog(null, "Please input a positive time in minutes.");
					maxTime = inputMaxTime();
				}
			} catch(NumberFormatException e)  {
				JOptionPane.showMessageDialog(null, "Please input a valid number.");
				maxTime = inputMaxTime();
				while(maxTime < 1)
			  	{
				  	JOptionPane.showMessageDialog(null, "Please input a positive time in minutes.");
				  	maxTime = inputMaxTime();
			   	}
			}
			
		    	inputVehicleType();
            		double carMileagePerGallon = 0.0d;
			if (vehicleType.equals("Gas")){
				try {
					carMileagePerGallon = inputGasMileage();
					while(carMileagePerGallon < 1)
					{
						JOptionPane.showMessageDialog(null, "Please input a positive mileage.");
						carMileagePerGallon = inputGasMileage();	
					}
				} catch(NumberFormatException el) {
					JOptionPane.showMessageDialog(null, "Please input a valid number for mileage.");
					carMileagePerGallon = inputGasMileage();	
					while(carMileagePerGallon < 1)
					{
						JOptionPane.showMessageDialog(null, "Please input a positive mileage.");
						carMileagePerGallon = inputGasMileage();
					}
				}
			}
			
			//Replace all spaces with + for the maps api
			String startAddress = startLocation.replace(' ', '+');  
			String destAddress = destLocation.replace(' ', '+');
			//Create routes array list
			routesList = new ArrayList<Route>();
                        
			//Get all routes information
			getRouteInfo(startAddress, destAddress, carMileagePerGallon, maxTime);
			
			//Get map image
			getMap(routesList);
                        
			//Sort array list
			sort = new Sort(routesList);
			if (sortBy.equals("Emission")) {
				routesList = sort.sortByEmission();
			}
			else if (sortBy.equals("Time")) {
				routesList = sort.sortByTime();
			}
			displayRoutes();
		}
        	else if (action.equals("Fun Facts")) {
			Runnable r = () -> {
            		/*
			String html = "<html><body width='%1s'><h1>Label Width</h1>"
                	+ "<p>Many Swing components support HTML 3.2 &amp; "
                	+ "(simple) CSS.  By setting a body width we can cause "
                	+ "the component to find the natural height needed to "
                	+ "display the component.<br><br>"
                	+ "<p>The body width in this text is set to %1s pixels.";
            		// change to alter the width 
			*/
			
			String funfacts = "There is more carbon dioxide in the atmosphere today than at any point in the last 800,000 years"
			+ "\nSince 1870, global sea levels have risen by about 8 inches"
			+ "\nThough Americans make up just 4 percent of the world's population, we produce 25 percent of the carbon dioxide pollution from fossil-fuel burning.";
			
			int w = 175;

            		JOptionPane.showMessageDialog(null, String.format(funfacts, w, w));
			
			};
			
			SwingUtilities.invokeLater(r);
        	}
        	else if (action.equals("Carbon Emissions")) {
			sortBy = "Emission";
			if (routesList != null) {
				routesList = sort.sortByEmission();
				displayRoutes();
			}
		}
		else if (action.equals("Time")) {
			sortBy = "Time";
			if(routesList != null) {
				routesList = sort.sortByTime();
				displayRoutes();
			} 
		}
	}
	
	public static void main(String[] args){
	///	JLabel lblNewLabel = new Jlabel("");
	///	lblNewLabel.setBounds(10,48,23,25);
	///	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	///	lblNewLabel.setIcon(new ImageIcon("environment.png"));
	
	///	JOptionPane.showMessageDialog(new UserInputCop(),
    	///             "Hi, I am ecofriendly!", "Customized Dialog",
       ///         JOptionPane.INFORMATION_MESSAGE, icon);
		
		///inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UserInput u = new UserInput();
		
		ImageIcon icon = new ImageIcon("green_day.png");
		JLabel background = new JLabel("",icon,JLabel.CENTER);
		u.getContentPane().setLayout(new GridLayout(1,2));
		panel = new JPanel();
		label = new JLabel("EcoTravel", SwingConstants.CENTER);
		label.setFont(new Font("Serif", Font.BOLD, 60)); //for testing 
		
	    	///label.setBounds(50, 50, 100, 30);
		
		factsButton.setPreferredSize(new Dimension(160, 45));  //for testing 
		inputButton.setPreferredSize(new Dimension(160, 45)); //for testing 
		///Dimension size = b.getPreferredSize();
		///Dimension size1 = button.getPreferredSize();
		///b.setBounds(600, 300, size.width, size.height);
		///button.setBounds(200, 300, size.width, size.height);
		
		//b.setBounds(300, 100, size.width, size.height);  //for testing 
		//button.setBounds(200, 100, size.width, size.height); //for testing 

		panel.setLayout(new GridBagLayout());  
		gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 40;
        	gbc.gridwidth = 5;
        	gbc.gridx = 0; 
        	gbc.gridy = 0;
		panel.add(label, gbc); 
		gbc.ipady = 0;  
		gbc.weightx = 0.5;
        	gbc.weighty = 0.5;
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(sortByLabel, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		panel.add(emissionsRadioButton, gbc);
		gbc.gridx = 3;
		gbc.gridy = 1;
		panel.add(timeRadioButton, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(inputButton, gbc);
		gbc.gridx = 4;
		gbc.gridy = 4;
		panel.add(factsButton, gbc);
		
		///panel.add(background);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	
     		u.setContentPane(panel);
		u.getContentPane().setBackground(Color.decode("#87CEEB"));
    		u.setVisible(true);
		u.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    
	}
	public static String inputStartLocation(){
		return (String)JOptionPane.showInputDialog(new UserInput(), "Input starting location address:", "Starting Location",
		JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	public static String inputDestLocation(){
		return (String)JOptionPane.showInputDialog(new UserInput(), "Input destination location address:", "Destination Location",
		JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	public static int inputMaxTime(){
		return Integer.valueOf((String)JOptionPane.showInputDialog(new UserInput(), "Input the maximum amount of time (in minutes) you have to travel:", "Max Time",
		JOptionPane.PLAIN_MESSAGE, null, null, null));
	}
	public static void inputVehicleType(){
            try {
                Object[] possibleValues = { "Gas", "Electric", "Hybrid" };
                Object selectedValue = JOptionPane.showInputDialog(null,
                "Select your personal vehicle type:", "Vehicle Type",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
                if (selectedValue == null) {
                    System.out.println("User selected cancel");
                }
                else {
                    vehicleType = selectedValue.toString();
                }
            }
            catch (NullPointerException e) {
                System.out.println("User selected cancel");
                System.exit(0);
            }
	}
	public static float inputGasMileage(){
		return Float.valueOf((String)JOptionPane.showInputDialog(new UserInput(), "Input the gas mileage (in miles/gallon) of your personal vehicle:", "Gas Mileage",
		JOptionPane.PLAIN_MESSAGE, null, null, null));
	}
	public static void displayRoutes() {
		//Remove previous routes info if on panel
    		List<Component> componentList = Arrays.asList(panel.getComponents());
    		if (componentList.contains(routesArea)) {
    			panel.remove(routesArea);
    		}
		//Initialize new text area and settings
		routesArea = new JTextArea();
		routesArea.setOpaque(false);
        	routesArea.setEditable(false);
		routesArea.setLineWrap(true);
        	routesArea.setWrapStyleWord(true);
		String routesInfo = "";
		//Add each routes' information to text area
		for (Route route : routesList) {
			routesInfo += route.getTransportType() + " Route\n"
					+ "\tDistance: " + route.getDistance() + " miles\n"
					+ "\tCarbon Emissions: " + route.getRouteEmissions() + " pounds\n"
					+ "\tTime: " + route.getTime() + " minutes\n"
					+ "\tDirections: " + route.getDirectionsList() + "\n";
		}
		routesArea.setText(routesInfo);
		//Place text area on panel
        	gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 5;
		panel.add(routesArea, gbc);
		panel.revalidate();
	}
	public void getRouteInfo(String startAddress, String destAddress, double carMileagePerGallon, int maxTime) {
		try {
                	//Generate a the routes using the user inputs
                	Route carRoute = new Route(startAddress, destAddress, "driving");
                	Route busRoute = new Route(startAddress, destAddress, "transit");
                	Route bicycleRoute = new Route(startAddress, destAddress, "bicycling");
                	Route walkingRoute = new Route(startAddress, destAddress, "walking");
                	//Add the routes to the list
                	ArrayList<Route> allRoutesList = new ArrayList<Route>();;
                	allRoutesList.add(carRoute);
                	allRoutesList.add(busRoute);
                	allRoutesList.add(bicycleRoute);
                	allRoutesList.add(walkingRoute);
            	 
            		double emissionsPerMile = 0.0;
                	//Connect to api and get all the route information
                	for (Route route: allRoutesList) {
                    		//Connect to the api
                    		route.connect();
                    		//Calculate emissions
                    		if (route.getTransportType().equals("driving")) {
                        		if (vehicleType.equals("Gas")) {
                                		emissionsPerMile = 19.6/carMileagePerGallon; 	//average gasoline CO2 emissions: 8887g/gal = 19.6lb/gal
                       			}
                        		else if (vehicleType.equals("Electric")) {
                                		emissionsPerMile = 0.35; 			//average electric CO2 emissions: 0.35lb/mi
                       			}
                        		else if (vehicleType.equals("Hybrid")){
                                		emissionsPerMile = 0.42; 			//average hybrid CO2 emissionsL 0.42lb/mi
                        		}
                    		}
                    		else if (route.getTransportType().equals("transit")) {
                        		emissionsPerMile = 0.25;                               //112.7g/mile average for buses = 0.25lb/mi
                    		}
                    		else {
                        		emissionsPerMile = 0.0;                                   //Biking and walking will have 0 emissionsPerMile
                    		}
                    		//Perform get request and set route information
                    		route.getRouteInfo(emissionsPerMile);
                   		 //Disconnect from the api
                    		route.disconnect();
                    
                    		//Add the route if it is within the user's time constraint
                    		if (route.getTime() <= maxTime) {
                        		routesList.add(route);
                   		}
                	}
            	}
           	catch (MalformedURLException e) {
                	System.out.println("Malformed url exception occurred");
                	e.printStackTrace();
            	}
            	catch (ProtocolException e) {
                	System.out.println("Protocol Exception occurred");
                	e.printStackTrace();
            	}
            	catch (IOException e) {
                	System.out.println("IO Exception occurred");
                	e.printStackTrace();
            	}
            	catch (ParseException e) {
                	System.out.println("Parse Exception occurred");
                	e.printStackTrace();
            	}  
        }

        public void getMap(ArrayList<Route> rl) {
		String uimg="https://maps.googleapis.com/maps/api/staticmap?size=" + 300 + "x" + 200;
		int i=0;
		for (Route r: rl) {
			uimg += "&path=color:" + colors[i++] + "|enc:" + r.getPoly();
		}
		uimg += "&key=" + rl.get(0).getKey();
		try {
			URL url=new URL(uimg);
			BufferedImage img= ImageIO.read(url);
			map=new JLabel(new ImageIcon(img));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		gbc.gridx = 2;
		gbc.gridy = 4;
		panel.add(map, gbc);
		panel.revalidate();
	}
}

