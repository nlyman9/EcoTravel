package com.ecotravel.main;

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
		inputVehicleType();
                if (vehicleType.equals("Gas")){
			int carMileagePerGallon = inputGasMileage(inputFrame);
		}
	}
	public static String inputStartLocation(final JFrame frame){
		return (String)JOptionPane.showInputDialog(frame, "Input starting location address:", "Starting Location",
		JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	public static String inputDestLocation(final JFrame frame){
		return (String)JOptionPane.showInputDialog(frame, "Input destination location address:", "Destination Location",
		JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	public static int inputMaxTime(final JFrame frame){
		return Integer.valueOf((String)JOptionPane.showInputDialog(frame, "Input the maximum amount of time (in minutes) you have to travel:", "Max Time",
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
	public static int inputGasMileage(final JFrame frame){
		return Integer.valueOf((String)JOptionPane.showInputDialog(frame, "Input the gas mileage (in miles/gallon) of your personal vehicle:", "Gas Mileage",
		JOptionPane.PLAIN_MESSAGE, null, null, null));
	}
}

