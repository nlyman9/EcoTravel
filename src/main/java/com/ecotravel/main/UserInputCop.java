package com.ecotravel.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserInputCop extends JFrame implements ActionListener
{
	private static String vehicleType;
	private static String sortBy;
	private static JPanel panel;
	///private JButton b;
	private static JButton factsButton;
	private static JButton inputButton;
	private static JLabel sortByLabel;
	private static JRadioButton emissionsRadioButton;
	private static JRadioButton timeRadioButton;
	private static JLabel label;
	//private static Image image;
	private static final String IMAGE_PATH = "https://previews.123rf.com/images/jannoon028/jannoon0281410/jannoon028141000642/33085360-green-eco-earth-green-earth-with-trees-vector-illustration.jpg";
 	private static JLabel funfacts;
	
	public UserInputCop() 
	{
		///this.getContentPane().setBackground(Color.CYAN);
		///this.setBackground(Color.CYAN);
       		setTitle("EcoTravel");
        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        	setResizable(false);
        	setSize(1000, 3000);
		factsButton = new JButton("Fun Facts");
		inputButton = new JButton("Input Route Info");
		
		//Sort Options
		sortByLabel = new JLabel("Sort routes by:");
		emissionsRadioButton = new JRadioButton("Carbon Emissions");
		emissionsRadioButton.setSelected(true);
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
		
        	///getContentPane().setLayout(null);
		///JLabel label1 = new JLabel("EcoTravel");
		 ///setVisible(true);
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
			
			try {
				int maxTime = inputMaxTime();
				while(maxTime < 1)
				{	
					JOptionPane.showMessageDialog(null, "Please input a positive time in minutes.");
					maxTime = inputMaxTime();
				}
			} catch(NumberFormatException e)  {
				JOptionPane.showMessageDialog(null, "Please input a valid number.");
				int maxTime = inputMaxTime();
				
				while(maxTime < 1)
			   {
				  JOptionPane.showMessageDialog(null, "Please input a positive time in minutes.");
				  maxTime = inputMaxTime();
			   }
			}
			
		    	inputVehicleType();
			if (vehicleType.equals("Gas")){
				try {
					int carMileagePerGallon = inputGasMileage();
					while(carMileagePerGallon < 1)
					{
						JOptionPane.showMessageDialog(null, "Please input a positive mileage.");
						carMileagePerGallon = inputGasMileage();	
					}
				} catch(NumberFormatException el) {
					JOptionPane.showMessageDialog(null, "Please input a valid number for mileage.");
					int carMileagePerGallon = inputGasMileage();	
					while(carMileagePerGallon < 1)
					{
						JOptionPane.showMessageDialog(null, "Please input a positive mileage.");
						carMileagePerGallon = inputGasMileage();
					}
				}
			}
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
		}
		else if (action.equals("Time")) {
			sortBy = "Time";
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
		
		UserInputCop u = new UserInputCop();
	
		/*
		panel = new JPanel();
		b = new JButton("Fun Facts");
		button = new JButton("EcoTravel Route Info");
		label = new JLabel("EcoTravel");
	    	label.setBounds(50, 50, 100, 30);
		label.setFont(new Font("Papyrus", Font.PLAIN, 100)); 
		///label.setText("EcoTravel");
		//b.setPreferredSize(new Dimension(10, 25));  //for testing 
		//button.setPreferredSize(new Dimension(10, 25)); //for testing 
		Dimension size = b.getPreferredSize();
		Dimension size1 = button.getPreferredSize();
		
		//b.setBounds(300, 100, size.width, size.height);  //for testing 
		//button.setBounds(200, 100, size.width, size.height); //for testing 
		
		b.setBounds(600, 400, size.width, size.height);
		button.setBounds(200, 400, size.width, size.height);
		
	    	b.setPreferredSize(new Dimension(160, 45));
	   	button.setPreferredSize(new Dimension(160, 45));
		
		
        	panel.setLayout(null);
		//panel.setLayout(new BorderLayout()); //for testing 
		
		
		panel.add(label);
		panel.add(b);
		panel.add(button);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		u.setContentPane(panel);
		u.getContentPane().setBackground(Color.CYAN);
    		u.setVisible(true);
		*/
	    
		
		//************************************************************************************************************ alternate code 
		
		ImageIcon icon = new ImageIcon("green_day.png");
		JLabel background = new JLabel("",icon,JLabel.CENTER);
		//background.setLayout( new FlowLayout(FlowLayout.LEFT) );
		
		
		
		
		u.getContentPane().setLayout(new GridLayout(1,2));
		panel = new JPanel();
	//	b = new JButton("Fun Facts");
	//	button = new JButton("EcoTravel Route Info");
		label = new JLabel("EcoTravel", SwingConstants.CENTER);
		label.setFont(new Font("Serif", Font.BOLD, 60)); //for testing 
		
		//label.setFont(new Font("Papyrus", Font.PLAIN, 100)); 
		
		/*GridBagConstraints gridBagConstraints = new GridBagConstraints();   
        	gridBagConstraints.anchor = GridBagConstraints.NORTH;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipady = 40;
        	gridBagConstraints.weightx = 2;
        	gridBagConstraints.weighty = 4;
        	gridBagConstraints.gridx = 3; //it was initially 0
        	gridBagConstraints.gridy = 0;
		
		
		GridBagConstraints gbc = new GridBagConstraints();   
        	gbc.fill = GridBagConstraints.HORIZONTAL;
		//gbc.ipady = 40;      //make this component tall
		gbc.weightx = 0.5;
		//gbc.gridwidth = 3;
		gbc.gridx = 2;
		gbc.gridy = 0;*/
		

		
	  	///  label.setBounds(50, 50, 100, 30);
		
		factsButton.setPreferredSize(new Dimension(160, 45));  //for testing 
		inputButton.setPreferredSize(new Dimension(160, 45)); //for testing 
		///Dimension size = b.getPreferredSize();
		///Dimension size1 = button.getPreferredSize();
		///b.setBounds(600, 300, size.width, size.height);
		///button.setBounds(200, 300, size.width, size.height);
		
		//b.setBounds(300, 100, size.width, size.height);  //for testing 
		//button.setBounds(200, 100, size.width, size.height); //for testing 
		
		
		///panel.setLayout(null);

		panel.setLayout(new GridBagLayout());  
		GridBagConstraints gbc = new GridBagConstraints();
		
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
		gbc.gridx = 3;
		gbc.gridy = 4;
		panel.add(factsButton, gbc);
		
		///panel.add(background);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	

     		u.setContentPane(panel);
		
		//u.getContentPane().add(panel);

		u.getContentPane().setBackground(Color.CYAN);
    		u.setVisible(true);
		u.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    

	}
	public static String inputStartLocation(){
		return (String)JOptionPane.showInputDialog(new UserInputCop(), "Input starting location address:", "Starting Location",
		JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	public static String inputDestLocation(){
		return (String)JOptionPane.showInputDialog(new UserInputCop(), "Input destination location address:", "Destination Location",
		JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	public static int inputMaxTime(){
		return Integer.valueOf((String)JOptionPane.showInputDialog(new UserInputCop(), "Input the maximum amount of time (in minutes) you have to travel:", "Max Time",
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
	public static int inputGasMileage(){
		return Integer.valueOf((String)JOptionPane.showInputDialog(new UserInputCop(), "Input the gas mileage (in miles/gallon) of your personal vehicle:", "Gas Mileage",
		JOptionPane.PLAIN_MESSAGE, null, null, null));
	}
}

