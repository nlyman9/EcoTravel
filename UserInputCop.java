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

public class UserInputCop extends JFrame{
	private static String vehicleType;
	
	 public UserInputCop() 
	 {
        getContentPane().setBackground(Color.CYAN);
        setTitle("EcoTravel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(1000, 3000);
        getContentPane().setLayout(null);
		JLabel label1 = new JLabel("EcoTravel");

     }
	
	
	
	public static void main(String[] args){
		
		////
		
		
		///inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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

