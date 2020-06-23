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

public class UserInput{
	private static String vehicleType;
	public static void main(String[] args){
		JFrame inputFrame = new JFrame("User Information");
		inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String startLocation = inputStartLocation(inputFrame);
		String destLocation = inputDestLocation(inputFrame);
		int maxTime = inputMaxTime(inputFrame);
		inputVehicleType(inputFrame, inputFrame.getContentPane());
		while(vehicleType == null){System.out.println("waiting");} //Loop that only works with print statement
		if (vehicleType.equals("gas")){
			int carMileagePerGallon = inputGasMileage(inputFrame);
			System.out.println(carMileagePerGallon);
		}
		
		//Debugging purposes
		System.out.println(startLocation);
		System.out.println(destLocation);
		System.out.println(maxTime);
		System.out.println(vehicleType);
	}
	public static String inputStartLocation(final JFrame frame){
		return (String)JOptionPane.showInputDialog(frame, "Input starting location address", "Starting Location",
		JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	public static String inputDestLocation(final JFrame frame){
		return (String)JOptionPane.showInputDialog(frame, "Input destination location address", "Destination Location",
		JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	public static int inputMaxTime(final JFrame frame){
		return Integer.valueOf((String)JOptionPane.showInputDialog(frame, "Input the maximum amount of time (in minutes) you have to travel", "Max Time",
		JOptionPane.PLAIN_MESSAGE, null, null, null));
	}
	public static void inputVehicleType(final JFrame frame, Container pane){
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		JLabel lbl = new JLabel ("Select your personal vehicle type:");
		pane.add(lbl);
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
	}
	public static int inputGasMileage(final JFrame frame){
		return Integer.valueOf((String)JOptionPane.showInputDialog(frame, "Input the gas mileage (in miles/gallon) of your personal vehicle i", "Gas Mileage",
		JOptionPane.PLAIN_MESSAGE, null, null, null));
	}
}

