import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.*;
import java.awt.FlowLayout;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;
import java.io.*;
import javax.imageio.*;
import java.net.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.WindowConstants;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.*;


public class UserInputCop extends JFrame implements ActionListener
{
	private static String vehicleType;
	private static JPanel panel;
	///private JButton b;
	private static JButton b;
	private static JButton button;
	private static JLabel label;
	//private static Image image;
	private static final String IMAGE_PATH = "https://previews.123rf.com/images/jannoon028/jannoon0281410/jannoon028141000642/33085360-green-eco-earth-green-earth-with-trees-vector-illustration.jpg";
    private static JLabel funfacts;
	
	 public UserInputCop() 
	 {
		/*
		getContentPane().setBackground(Color.CYAN);
        setTitle("EcoTravel");
       /// 
        setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1000, 3000);
        getContentPane().setLayout(null);
		
		panel = new JPanel();
		///b.setBounds(100, 150, 70, 30);

		///panel.setBounds(800, 800, 200, 100);

	    		
		
	///	JButton b = new JButton("Facts");
		
	 	b.setPreferredSize(new Dimension(160, 45));
	  ///  b.setLocation(20, 20);
	  
	    /// panel.setLayout(null);

		 b.setLayout(null);
		

	    panel.add(b);
	///	add(b, BorderLayout.CENTER); ////debug 
		this.getContentPane().add(panel);
		setVisible(true);
		getContentPane().setLayout(new FlowLayout());
		
		/// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		
		//********************************************************************************************* testing alternate code 
		
		
		///this.getContentPane().setBackground(Color.CYAN);
		///this.setBackground(Color.CYAN);
        setTitle("EcoTravel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setResizable(false);
        setSize(1000, 3000);
		b = new JButton("Fun Facts");
		button = new JButton("EcoTravel Route Info");
		
		b.addActionListener(this);
		button.addActionListener(this);
		
        ///getContentPane().setLayout(null);
		///JLabel label1 = new JLabel("EcoTravel");
		 ///setVisible(true);
		
     }
	 
	 @Override
    public void actionPerformed(ActionEvent ae) 
	{
        String action = ae.getActionCommand();
        if (action.equals("EcoTravel Route Info")) 
		{
            String startLocation = inputStartLocation();
		String destLocation = inputDestLocation();
		int maxTime = inputMaxTime();
		inputVehicleType(new UserInputCop());
		//while(vehicleType == null){System.out.println("waiting");} //Loop that only works with print statement
		
                if (vehicleType.equals("Gas")){
			int carMileagePerGallon = inputGasMileage();
			System.out.println(carMileagePerGallon);
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
		
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();   
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
		gbc.gridy = 0;
		

		
	  ///  label.setBounds(50, 50, 100, 30);
		
		b.setPreferredSize(new Dimension(160, 45));  //for testing 
		button.setPreferredSize(new Dimension(160, 45)); //for testing 
		///Dimension size = b.getPreferredSize();
		///Dimension size1 = button.getPreferredSize();
		///b.setBounds(600, 300, size.width, size.height);
		///button.setBounds(200, 300, size.width, size.height);
		
		//b.setBounds(300, 100, size.width, size.height);  //for testing 
		//button.setBounds(200, 100, size.width, size.height); //for testing 
		
		
		///panel.setLayout(null);

		panel.setLayout(new GridBagLayout()); //for testing 
		
		
		panel.add(label, gridBagConstraints);
		panel.add(b);
		panel.add(button, gbc);
		///panel.add(background);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	

     	u.setContentPane(panel);
		
		//u.getContentPane().add(panel);

		u.getContentPane().setBackground(Color.CYAN);
    	u.setVisible(true);
		u.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    
		
		
		/*
		String startLocation = inputStartLocation();
		String destLocation = inputDestLocation();
		int maxTime = inputMaxTime();
		inputVehicleType(new UserInputCop());
		//while(vehicleType == null){System.out.println("waiting");} //Loop that only works with print statement
		
                if (vehicleType.equals("Gas")){
			int carMileagePerGallon = inputGasMileage();
			System.out.println(carMileagePerGallon);
		}
		
		
		//Debugging purposes
		System.out.println(startLocation);
		System.out.println(destLocation);
		System.out.println(maxTime);
		System.out.println(vehicleType);
		*/
		
		
	}
		
	public static String inputStartLocation(){
		return (String)JOptionPane.showInputDialog(new UserInputCop(), "Input starting location address", "Starting Location",
		JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	public static String inputDestLocation(){
		return (String)JOptionPane.showInputDialog(new UserInputCop(), "Input destination location address", "Destination Location",
		JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	public static int inputMaxTime(){
		return Integer.valueOf((String)JOptionPane.showInputDialog(new UserInputCop(), "Input the maximum amount of time (in minutes) you have to travel", "Max Time",
		JOptionPane.PLAIN_MESSAGE, null, null, null));
	}
	public static void inputVehicleType(JFrame frame){
            /*
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		JLabel lbl = new JLabel ("Select your personal vehicle type:");
		pane.add(lbl);
                System.out.println("This is happening");
		JButton gasButton = new JButton("Gas");
		gasButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(gasButton);
        gasButton.addActionListener(new ActionListener(){
			@Override
            public void actionPerformed(ActionEvent e) {
				vehicleType = "gas";
				frame.dispose();
            }
         });
        JButton electricButton = new JButton("Electric");
		electricButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(electricButton);
        electricButton.addActionListener(new ActionListener(){
			@Override
            public void actionPerformed(ActionEvent e) {
				vehicleType = "electric";
				frame.dispose();
            }
         });
        JButton hybridButton = new JButton("Hybrid");
		hybridButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(hybridButton);
        hybridButton.addActionListener(new ActionListener(){
			@Override
            public void actionPerformed(ActionEvent e) {
				vehicleType = "hybrid";
				frame.dispose();
            }
         });    
        frame.pack();
        frame.setVisible(true);
        */
            Object[] possibleValues = { "Gas", "Electric", "Hybrid" };
            Object selectedValue = JOptionPane.showInputDialog(null,
            "Choose a vehicle type", "Vehicle Type",
            JOptionPane.INFORMATION_MESSAGE, null,
            possibleValues, possibleValues[0]);
            if (selectedValue.toString() == null) {
                System.out.println("User selected cancel");
            }
            else {
                vehicleType = selectedValue.toString();
            }
	}
	public static int inputGasMileage() {
		return Integer.valueOf((String)JOptionPane.showInputDialog(new UserInputCop(), "Input the gas mileage (in miles/gallon) of your personal vehicle i", "Gas Mileage",
		JOptionPane.PLAIN_MESSAGE, null, null, null));
	}
}

